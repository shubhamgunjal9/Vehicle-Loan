package com.GCappps.loanFin.app.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Component
public class LoanDisbursement {
	@Id
	private String loanId;
	private Double totalAmount;
	private Long dealarAccountNumber;
	private String dealerIFSCCode;
	private Date amountPaidDate;
}
