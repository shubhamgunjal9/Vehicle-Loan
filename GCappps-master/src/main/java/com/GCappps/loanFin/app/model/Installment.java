package com.GCappps.loanFin.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import java.time.Month;
import java.util.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Installment {
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private Integer installmentId;
	private Integer installmentNumber;
	private String installmentMonth;
	private Date installementPaidDate;
	private String paymentStatus;
	
}
