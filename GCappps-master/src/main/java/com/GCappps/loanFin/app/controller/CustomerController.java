package com.GCappps.loanFin.app.controller;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.GCappps.loanFin.app.enums.CustomerEnum;
import com.GCappps.loanFin.app.enums.EnquiryStatus;
import com.GCappps.loanFin.app.model.Cibil;
import com.GCappps.loanFin.app.model.Customer;
import com.GCappps.loanFin.app.model.Dealer;
import com.GCappps.loanFin.app.model.Documents;
import com.GCappps.loanFin.app.model.EnquiryDetails;
import com.GCappps.loanFin.app.model.Ledger;
import com.GCappps.loanFin.app.responce.BaseResponce;
import com.GCappps.loanFin.app.serviceI.CustomerServiceI;
import com.GCappps.loanFin.app.serviceI.EnquiryServiceI;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/GCappps")
@CrossOrigin("*")
public class CustomerController {

	@Autowired
	EnquiryServiceI enquiryServiceI;

	@Autowired
	CustomerServiceI customerServiceI;

	// http://localhost:9090/GCappps/upload
	@PostMapping(value = "/upload/{enquiryId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<BaseResponce<Customer>> saveCustomer(@RequestPart(value = "pancard") MultipartFile pancard,
			@RequestPart(value = "adharcard") MultipartFile adharcard,
			@RequestPart(value = "photo") MultipartFile photo,
			@RequestPart(value = "signature") MultipartFile signature,
			@RequestPart(value = "incomeStatement") MultipartFile incomeStatement,
			@RequestPart(value = "customerData") String customerData, @PathVariable String enquiryId) {
		ObjectMapper om = new ObjectMapper();
		try {
			Customer customerRead = om.readValue(customerData, Customer.class);
			System.err.println(customerData);
			Optional<EnquiryDetails> enquiry1 = enquiryServiceI.customerLogin(enquiryId);

			if (enquiry1.isEmpty()) {

				BaseResponce<Customer> base = new BaseResponce<>(200, "You are not eligible Customer ", customerRead);
				return new ResponseEntity<BaseResponce<Customer>>(base, HttpStatus.OK);

			}
			EnquiryDetails enqu = enquiry1.get();
			customerRead.setCustomerId("GCappps-cust-" + ThreadLocalRandom.current().nextInt(100, 200));
			customerRead.getDealerData().setDealerId("GCappps-Dealer-" + ThreadLocalRandom.current().nextInt(100, 200));

			if (customerRead.getEnquiryId().equals(enqu.getEnquiryId())
					&& enqu.getEnquiryStatus().equals(String.valueOf(EnquiryStatus.Cibilok))) {

				customerRead.setCustomerCibilScore(enqu.getCibilData());
				customerRead.setLoanDisbursement(null);
				customerRead.setSanctionLetter(null);
				customerRead.setLedger(null);
				customerRead.setCustomerVerificationStatus(String.valueOf(CustomerEnum.Applied));

				Documents d1 = new Documents();

				d1.setPancard(pancard.getBytes());
				d1.setAdharcard(adharcard.getBytes());
				d1.setPhoto(photo.getBytes());
				d1.setSignature(signature.getBytes());
				d1.setIncomeStatement(incomeStatement.getBytes());
				// customer.setPancard(file2.getBytes());
				customerRead.setCustomerDocuments(d1);

				Customer customer2 = customerServiceI.saveCustomer(customerRead);

				BaseResponce<Customer> base = new BaseResponce<>(201, "Customer Save successfully", customer2);
				return new ResponseEntity<BaseResponce<Customer>>(base, HttpStatus.CREATED);

			} else {
				BaseResponce<Customer> base = new BaseResponce<>(406, "You are not eligible Customer ", customerRead);
				return new ResponseEntity<BaseResponce<Customer>>(base, HttpStatus.NOT_ACCEPTABLE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			BaseResponce<Customer> base = new BaseResponce<>(406, "You are not eligible Customer ", null);
			return new ResponseEntity<BaseResponce<Customer>>(base, HttpStatus.NOT_ACCEPTABLE);
		}

	}

//http://localhost:9090/GCappps/getAllCustomer/{customerVerificationStatus}
	@GetMapping("/getAllCustomer/{customerVerificationStatus}")
	public ResponseEntity<BaseResponce<List<Customer>>> getAllCustomer(
			@PathVariable String customerVerificationStatus) {

		List<Customer> list = customerServiceI.getCustomer(customerVerificationStatus);
		BaseResponce<List<Customer>> base = new BaseResponce<>(200, "All customer Fetch by status", list);
		return new ResponseEntity<BaseResponce<List<Customer>>>(base, HttpStatus.OK);
	}

//http://localhost:9090/GCappps/update/{customerId}
	@PutMapping(value = "/update/{customerId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<BaseResponce<Customer>> updateCustomer(@RequestPart(value = "pancard") MultipartFile pancard,
			@RequestPart(value = "adharcard") MultipartFile adharcard,
			@RequestPart(value = "photo") MultipartFile photo,
			@RequestPart(value = "signature") MultipartFile signature,
			@RequestPart(value = "incomeStatement") MultipartFile incomeStatement,
			@RequestPart(value = "customerData") String customerData,
			@PathVariable(value = "customerId") String customerId) {

		Optional<Customer> customer1 = customerServiceI.getOneCustomer(customerId);
		Customer customer2 = customer1.get();
		ObjectMapper om = new ObjectMapper();
		try {
			Customer customerRead = om.readValue(customerData, Customer.class);
			customer2.setCustomerId(customerRead.getCustomerId());
			Documents d1 = new Documents();
			d1.setPancard(pancard.getBytes());
			d1.setAdharcard(adharcard.getBytes());
			d1.setPhoto(photo.getBytes());
			d1.setSignature(signature.getBytes());
			d1.setIncomeStatement(incomeStatement.getBytes());
			// customer.setPancard(file2.getBytes());
			customerRead.setCustomerDocuments(d1);
			Customer cust11 = customerServiceI.updateCustomer(customerRead);
			BaseResponce<Customer> base = new BaseResponce<>(201, "Customer Save successfully", cust11);
			return new ResponseEntity<BaseResponce<Customer>>(base, HttpStatus.CREATED);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<BaseResponce<Customer>>(HttpStatus.NOT_FOUND);
		}

	}

	// oe ,user ,ac dis
	@PutMapping("/withstatus/{customerId}")
	public ResponseEntity<BaseResponce<Customer>> withstatusUpdate(@RequestBody String customerVerfivcationstatus,
			@PathVariable String customerId) {

		Optional<Customer> customer1 = customerServiceI.getOneCustomer(customerId);
		if (customer1.isPresent()) {
			Customer customer2 = customer1.get();

			customer2.setCustomerVerificationStatus(customerVerfivcationstatus);//

			Customer customer3 = customerServiceI.withoutDoc(customer2);
			System.out.println("newly updated status " + customer3.getCustomerVerificationStatus());
			BaseResponce<Customer> base = new BaseResponce<>(201, "Customer Update successfully", customer3);
			return new ResponseEntity<BaseResponce<Customer>>(base, HttpStatus.CREATED);

		}
		BaseResponce<Customer> base = new BaseResponce<>(404, "Customer Not Updated", null);
		return new ResponseEntity<BaseResponce<Customer>>(base, HttpStatus.NOT_FOUND);
	}
//	@PutMapping("/withoutdocupdate/{customerId}")
//	public ResponseEntity<BaseResponce<Customer>> withoutDoc(@RequestBody Customer customer,
//			@PathVariable String customerId) {
//
//		Optional<Customer> customer1 = customerServiceI.getOneCustomer(customerId);
//		if (customer1.isPresent()) {
//			Customer customer2 = customer1.get();
//
//			Customer customer3 = customerServiceI.withoutDoc(customer2);
//			BaseResponce<Customer> base = new BaseResponce<>(201, "Customer Update successfully", customer3);
//			return new ResponseEntity<BaseResponce<Customer>>(base, HttpStatus.CREATED);
//
//		}
//		BaseResponce<Customer> base = new BaseResponce<>(404, "Customer Not Updated", null);
//		return new ResponseEntity<BaseResponce<Customer>>(base, HttpStatus.NOT_FOUND);
//	}

	@GetMapping("/getcustomerbyid/{customerId}")
	public ResponseEntity<BaseResponce<Customer>> getOneCustomer(@PathVariable String customerId) {

		Optional<Customer> custOpt = customerServiceI.getOneCustomer(customerId);
		if(custOpt.isPresent()) {
			Customer customer = custOpt.get();
			BaseResponce<Customer> base = new BaseResponce<>(200, "Customer Fetch successfully", customer);
			return new ResponseEntity<BaseResponce<Customer>>(base, HttpStatus.OK);
		
		}
		else {
				BaseResponce<Customer> base = new BaseResponce<>(404, "Customer Not Found", null);
			return new ResponseEntity<BaseResponce<Customer>>(base, HttpStatus.NOT_FOUND);
		
		}
		}
}
