package com.GCappps.loanFin.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.GCappps.loanFin.app.model.Customer;
import com.GCappps.loanFin.app.model.SanctionLetter;
import com.GCappps.loanFin.app.responce.BaseResponce;
import com.GCappps.loanFin.app.serviceI.SanctionLetterService;

@RestController
@CrossOrigin("*")
@RequestMapping("/GCappps")

public class SanctionLetterController {
	
	@Autowired
	SanctionLetterService sanLetter;
	//http://localhost:9090/GCappps/generatesanctionletter/{customerId}
	@PostMapping("/generatesanctionletter/{customerId}")
	public ResponseEntity<BaseResponce<SanctionLetter>> generatesanction(@RequestBody SanctionLetter sanctionLetter,@PathVariable String customerId){
		SanctionLetter san=sanLetter.generatesanction(sanctionLetter,customerId);
		BaseResponce<SanctionLetter> base=new BaseResponce<>(200,"Sanction Letter is generated",san);
		
		return new ResponseEntity<BaseResponce<SanctionLetter>>(base,HttpStatus.CREATED);
	}

}
