package com.GCappps.loanFin.app.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Dealer {
	@Id
	private String dealerId;
	private String dealerName;
	private String dealerLocation;
	private String dealerEmail;
	private Long accountNumber;
	private String accountIFSCCode;
	private String vehicleCompanyName;
	private String vehicleModel;
	private Double vehiclePrice;
	

}
