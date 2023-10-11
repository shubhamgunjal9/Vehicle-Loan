package com.GCappps.loanFin.app.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.GCappps.loanFin.app.enums.CustomerEnum;
import com.GCappps.loanFin.app.model.Customer;
import com.GCappps.loanFin.app.model.Ledger;
import com.GCappps.loanFin.app.responce.BaseResponce;
import com.GCappps.loanFin.app.serviceI.CustomerServiceI;
import com.GCappps.loanFin.app.serviceI.LedgerServiceI;

@RestController
@CrossOrigin("*")
@RequestMapping("/GCappps")
public class LedgerController {
	
	@Autowired
	LedgerServiceI ledservice;
	
	@Autowired
	CustomerServiceI custService;
	
	//http://localhost:9090/GCappps/generateledger
	@GetMapping("/generateledger/{customerId}")
	public ResponseEntity<BaseResponce<Ledger>> ledgergeneration(@PathVariable String customerId){
		Optional<Customer> cust=custService.getOneCustomer(customerId);
		if(cust.isPresent()) {
		Customer customer=cust.get();
		Ledger ledger=ledservice.ledgergeneration(customer);
		BaseResponce<Ledger> base=new BaseResponce<>(200,"Ledger is generated",ledger);
		return new ResponseEntity<BaseResponce<Ledger>>(base,HttpStatus.CREATED);
		}
		else {
			BaseResponce<Ledger> base=new BaseResponce<>(200,"Required customer is not present",null);
			return new ResponseEntity<BaseResponce<Ledger>>(base,HttpStatus.CREATED);
		}
	}
	//http://localhost:9090/GCappps/payinstallment/{installmentnumber}
	@PutMapping("/payinstallment/{installmentnumber}")
	public ResponseEntity<BaseResponce<Ledger>> payinstallment(@RequestBody Ledger ledger,@PathVariable Integer installmentnumber){
		Ledger led=ledservice.payinstallment(ledger,installmentnumber);
		BaseResponce<Ledger> base=new BaseResponce<>(200,"Installment number "+installmentnumber +" is paid",led);
		return new ResponseEntity<BaseResponce<Ledger>>(base,HttpStatus.CREATED);
	}
	
	@PutMapping("/unpayinstallment/{installmentnumber}")
	public ResponseEntity<BaseResponce<Ledger>> unpayinstallment(@RequestBody Ledger ledger,@PathVariable Integer installmentnumber){
		Ledger led=ledservice.unpayinstallment(ledger,installmentnumber);
		BaseResponce<Ledger> base=new BaseResponce<>(200,"Installment number "+installmentnumber +" is Unpaid",led);
		return new ResponseEntity<BaseResponce<Ledger>>(base,HttpStatus.CREATED);
	}
	
	@GetMapping("/getledger")
	public ResponseEntity<BaseResponce<Ledger>> getLedger(@RequestBody Customer customer)
	{
		if(String.valueOf(CustomerEnum.Customer_Accepted).equals(customer.getCustomerVerificationStatus())) 
		{
		Optional<Ledger> led=ledservice.getLedger(customer);
		Ledger ledger=led.get();
		BaseResponce<Ledger> base=new BaseResponce<>(201,"Required ledger is present",ledger);
		return new  ResponseEntity<BaseResponce<Ledger>>(base,HttpStatus.OK);
		}
		else 
		{
			BaseResponce<Ledger> base=new BaseResponce<>(201,"Respective Customer did not accpeted Sanction Letter",null);
			return new  ResponseEntity<BaseResponce<Ledger>>(base,HttpStatus.OK);
		}
	}

}
