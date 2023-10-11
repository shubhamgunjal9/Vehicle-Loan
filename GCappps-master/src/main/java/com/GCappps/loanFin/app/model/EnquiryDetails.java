package com.GCappps.loanFin.app.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Component
public class EnquiryDetails {

	@Id
	private String enquiryId;
	private String customerFirstName;
	private String customerMiddleName;
	private String customerLastName;
	private String customerDateOfBirth;
	private String customerEmail;
	private Long customerMobileNumber;
	private String pancardNumber;
	private String enquiryStatus;
	private Integer cibilScore;
	private Double monthlyIncome;
	@OneToOne(cascade = CascadeType.ALL)
	private Cibil cibilData;
}
