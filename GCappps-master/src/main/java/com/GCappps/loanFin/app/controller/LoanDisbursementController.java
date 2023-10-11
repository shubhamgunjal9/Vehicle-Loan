package com.GCappps.loanFin.app.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.GCappps.loanFin.app.model.Customer;
import com.GCappps.loanFin.app.model.LoanDisbursement;
import com.GCappps.loanFin.app.responce.BaseResponce;
import com.GCappps.loanFin.app.serviceI.CustomerServiceI;
import com.GCappps.loanFin.app.serviceI.LoanDisbursementserviceI;

@RestController
@CrossOrigin("*")
@RequestMapping("/GCappps")
public class LoanDisbursementController {
	
	@Autowired
	LoanDisbursementserviceI loandisb;
	

	@Autowired
	CustomerServiceI custService;
	

	//http://localhost:9090/GCappps/loandisburse
	@GetMapping("/loandisburse/{customerId}")
	public ResponseEntity<BaseResponce<LoanDisbursement>> loandisbursement(@PathVariable String customerId){
		Optional<Customer> cust=custService.getOneCustomer(customerId);
		if(cust.isPresent()) {
		Customer customer=cust.get();
		LoanDisbursement loandisuDisbursement=loandisb.loandisbursement(customer);
		BaseResponce<LoanDisbursement> base=new BaseResponce<>(200,"Loan Disbursement letter is generated",loandisuDisbursement);
		return new ResponseEntity<BaseResponce<LoanDisbursement>>(base,HttpStatus.OK);
		}
		else {
			BaseResponce<LoanDisbursement> base=new BaseResponce<>(200,"Required customer is not present",null);
			return new ResponseEntity<BaseResponce<LoanDisbursement>>(base,HttpStatus.OK);
		}
		
		
	}

}
