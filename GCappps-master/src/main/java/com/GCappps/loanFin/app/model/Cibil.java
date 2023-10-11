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
public class Cibil {

	@Id
	private String cibilId;
	private Integer cibilScore;
	private Date cibilGeneratedDate;
	private String remark;
	private Integer previousEmi;
}
