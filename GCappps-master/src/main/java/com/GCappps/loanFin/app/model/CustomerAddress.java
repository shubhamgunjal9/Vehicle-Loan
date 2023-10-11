package com.GCappps.loanFin.app.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CustomerAddress {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer addressId;
	private Integer localHouseNumber;
	private String localAreaName;
	private String localStreetName;
	private String localCityName;
	private String localDistrict;
	private Long localPincode;
	private String localState;

	private Integer permanentHouseNumber;
	private String permanentAreaName;
	private String permanentStreetName;
	private String permanentCityName;
	private String permanentDistrict;
	private Long permanentPincode;
	private String permanentState;

}
