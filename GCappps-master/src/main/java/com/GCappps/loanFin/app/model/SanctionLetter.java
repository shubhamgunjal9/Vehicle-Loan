package com.GCappps.loanFin.app.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SanctionLetter {

	@Id
	private String sanctionId;
	private Date sanctionDate;
	private String applicantName;
	private Double loanAmountSanctioned;
	private String interestType;
	private Double rateOfInterest;
	private Integer loanTenure;
	private Double monthlyEmiAmount;
	private Double loanAmountWithInterest;
	private String modeOfPayment;
	private String sanctionLetterStatus;

}
