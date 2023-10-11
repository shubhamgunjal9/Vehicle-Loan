package com.GCappps.loanFin.app.serviceImpl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.GCappps.loanFin.app.model.Emailattach;
import com.GCappps.loanFin.app.model.EnquiryDetails;
import com.GCappps.loanFin.app.model.SimpleMail;
import com.GCappps.loanFin.app.repository.MailRepository;
import com.GCappps.loanFin.app.serviceI.MailServiceI;

@Service
public class MailServiceImpl implements MailServiceI{
	
	@Autowired
	JavaMailSender mailsender;
	
	@Autowired
	MailRepository mailrepo;

	@Override
	public void sendmail(SimpleMail mail) {
		SimpleMailMessage message=new SimpleMailMessage();
		message.setFrom(mail.getFromEmail());
		message.setTo(mail.getToEmail());
		message.setSubject(mail.getSubject());
		message.setText(message.getText());
		mailsender.send(message);
		 mailrepo.save(mail);
	}
	@Override
	public void sendattachment(Emailattach mail, String filename) {
		// TODO Auto-generated method stub
		MimeMessage mimemessage=mailsender.createMimeMessage();
		try {
			MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimemessage,true);
			mimeMessageHelper.setFrom("pratap831@gmail.com");
			mimeMessageHelper.setTo(mail.getToEmail());
			mimeMessageHelper.setSubject(mail.getSubject());
			mimeMessageHelper.setText(mail.getText());
			
//			FileSystemResource fileSystemResource=new FileSystemResource(new File(eattch.getAttachment()));
			ByteArrayResource bytearray=new ByteArrayResource(mail.getAttachment());
		 
			
			mimeMessageHelper.addAttachment(filename,bytearray);
//			mimeMessageHelper.addAttachment("abac.jpg",new ByteArrayResource(eattch.getAttachment()));
			
			mailsender.send(mimemessage);
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void sendcibilstatus(EnquiryDetails enquiryDetails) {
		// TODO Auto-generated method stub
		SimpleMailMessage message=new SimpleMailMessage();
		message.setFrom("pratap831@gmail.com");
		message.setTo(enquiryDetails.getCustomerEmail());
		message.setSubject("Regarding GCappps loan enquiry");
		Integer cibil=enquiryDetails.getCibilScore();
		double monthlyIncome=enquiryDetails.getMonthlyIncome();
		double maximunloan=((12*monthlyIncome)*2.5);
		double emi=((monthlyIncome)/2)-((enquiryDetails.getCibilData().getPreviousEmi())*(0.75));
		if(cibil>700) {
			message.setText("Respected Customer, \n \n         We GCappps family happy to announce that after primary verification of CIBIL Data we found your cibil score " +cibil+" is satisfied, therefore you are eligible to get loan from our FinTech. \n       Get in association RE for further process. \n \n Your Enquiry Id is "+enquiryDetails.getEnquiryId() +"\n"
					+ "Maximum Loan GCappps can provide as per your monthly income="+maximunloan +"\n"
							+ "Maximum tentative monthly EMI GCappps can arrange after adjusting your Previous EMI="+emi);
		}
		else {
			message.setText("Respected Customer,we GCappps family regret to inform you based on your primary verification of CIBIL Data we found your cibil score " +cibil+" is unsatisfied,therefore you are not eligible to get loan from our FinTech.Hope you will remain associate with GCappps.");
		}
		message.setText(message.getText());
		mailsender.send(message);
	 
	}
	 
	 

}
