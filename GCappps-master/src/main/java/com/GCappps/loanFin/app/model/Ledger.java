package com.GCappps.loanFin.app.model;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Component
public class Ledger {
	@Id
	private String ledgerId;
	private String ledgerCreatedDate;
	private Double totalLoanAmount;
	private Integer tenure;
	private Double monthlyEMI;
	private Double amountPaidTillDate;
	private Double remainingAmount;
	private Integer defaulterCount;
	private String loanEndDate;
	private String loanStatus;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Installment> installments; 
}
