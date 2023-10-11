package com.GCappps.loanFin.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CustomerFinancialData {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerFinancialId;
	private Double customerTotalLoanRequired;
	private Long customerAccountNumber;
	private String bankName;
	private String accountHolderName;
	private String ifscCode;
	private String ocupationType;
	private String designation;
	private Double monthlyIncome;
	private String employerName;
	
}
