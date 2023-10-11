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
public class GuarantorDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer guarantorId;
	private String guarantorName;
	private String guarantorRelation;
	private Long guarantorMobileNumber;
	private Long guarantorAdharcardNumber;
	private String guarantorAddress;

}
