package com.GCappps.loanFin.app.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SimpleMail {
	@Id
	private String toEmail;
	private String fromEmail;
	private String subject;
	private String text;

}
