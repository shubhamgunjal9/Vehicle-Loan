package com.GCappps.loanFin.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emailattach {
	private String toEmail;
	private String fromEmail;
	private String subject;
	private String text;
	private byte[] attachment;

}
