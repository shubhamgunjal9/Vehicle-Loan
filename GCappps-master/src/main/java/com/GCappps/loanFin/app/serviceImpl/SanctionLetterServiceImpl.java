package com.GCappps.loanFin.app.serviceImpl;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.GCappps.loanFin.app.enums.CustomerEnum;
import com.GCappps.loanFin.app.model.Customer;
import com.GCappps.loanFin.app.model.SanctionLetter;
import com.GCappps.loanFin.app.repository.CustomerRepository;
import com.GCappps.loanFin.app.repository.SanctionLetterRepository;
import com.GCappps.loanFin.app.serviceI.SanctionLetterService;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class SanctionLetterServiceImpl implements SanctionLetterService{
	
	@Autowired
	SanctionLetterRepository slr;
	
	@Autowired
	CustomerRepository custrepo;
	
	@Autowired
	JavaMailSender mailsender;

	private Logger logger=LoggerFactory.getLogger(SanctionLetterServiceImpl.class);
	@Override
	public SanctionLetter generatesanction(SanctionLetter sanctionLetter, String customerId){
		Optional<Customer> cust=custrepo.findById(customerId);
		Customer customer=cust.get(); 
		sanctionLetter.setSanctionId("GCappps_Sanction_Letter-"+ThreadLocalRandom.current().nextInt(999,9999));
		Date date=new Date();
		sanctionLetter.setSanctionDate(date);
		sanctionLetter.setApplicantName(customer.getCustomerFirstName()+" "+customer.getCustomerMiddleName()+" "+customer.getCustomerLastName());
		sanctionLetter.setSanctionLetterStatus("Approved");
		SanctionLetter san= slr.save(sanctionLetter);
		customer.setSanctionLetter(san);
		customer.setCustomerVerificationStatus(String.valueOf(CustomerEnum.Sanction_Genetrated));
		custrepo.save(customer);
		
		logger.info("Sanction Letter pdf started");
		String title="GCapps-Get the key of happiness";
		String content1="Respected Cutomer "+customer.getCustomerFirstName()+" "+customer.getCustomerLastName();
		String content2="We GCapps family happy to inform that your requested loan approval is in-principle approved.Following are the information your loan approval.";
//		String content3="Loan Sanction id="+customer.getSanctionLetter().getSanctionId();
//		String content4="Loan amount="+customer.getSanctionLetter().getLoanAmountSanctioned();
//		String content5="Loan interst rate="+customer.getSanctionLetter().getRateOfInterest();
//		String content6="Loan interst Type="+customer.getSanctionLetter().getInterestType();
//		String content7="Loan interst Tenure="+customer.getSanctionLetter().getLoanTenure();
//		String content8="EMI amount="+customer.getSanctionLetter().getMonthlyEmiAmount();
//		String content9="Total loan Amount with interest="+customer.getSanctionLetter().getLoanAmountWithInterest();
		
		
		ByteArrayOutputStream out=new ByteArrayOutputStream();
		Document document=new Document();
		
		PdfWriter.getInstance(document, out);
		document.open();
		
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setColor(CMYKColor.BLUE);
		

		Font font1 = FontFactory.getFont(FontFactory.TIMES);
		font.setColor(25, 15, 30);
		
		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100f);
		table.setWidths(new int[] { 2, 2});
		table.setSpacingBefore(10);
		
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(CMYKColor.LIGHT_GRAY);
		cell.setPadding(5);
		
		cell.setPhrase(new Phrase("Loan Sanction id", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase(customer.getSanctionLetter().getSanctionId(), font1));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Loan amount", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase(String.valueOf(customer.getSanctionLetter().getLoanAmountSanctioned()), font1));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Loan interst rate", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase(String.valueOf(customer.getSanctionLetter().getRateOfInterest()), font1));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Loan interst Type", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase(String.valueOf(customer.getSanctionLetter().getInterestType()), font1));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Loan interst Tenure", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase(String.valueOf(customer.getSanctionLetter().getLoanTenure()), font1));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Monthly EMI amount", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase(String.valueOf(customer.getSanctionLetter().getMonthlyEmiAmount()), font1));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Total loan Amount with interest", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase(String.valueOf(customer.getSanctionLetter().getLoanAmountWithInterest()), font1));
		table.addCell(cell);
		
		Font titlefont = FontFactory.getFont(FontFactory.TIMES_BOLD,25);
		titlefont.setColor(186, 2, 237);
		Paragraph titlepara=new Paragraph(title,titlefont);
		titlepara.setAlignment(Element.ALIGN_CENTER);
		document.add(titlepara);
		Paragraph paracontent1=new Paragraph(content1);
		document.add(paracontent1);
		Paragraph paracontent2=new Paragraph(content2);
		document.add(paracontent2);
//		Paragraph paracontent3=new Paragraph(content3);
//		document.add(paracontent3);
//		Paragraph paracontent4=new Paragraph(content4);
//		document.add(paracontent4);
//		Paragraph paracontent5=new Paragraph(content5);
//		document.add(paracontent5);
//		Paragraph paracontent6=new Paragraph(content6);
//		document.add(paracontent6);
//		Paragraph paracontent7=new Paragraph(content7);
//		document.add(paracontent7);
//		Paragraph paracontent8=new Paragraph(content8);
//		document.add(paracontent8);
//		Paragraph paracontent9=new Paragraph(content9);
//		document.add(paracontent9);
		document.add(table);
		document.close();		
		ByteArrayInputStream byt=new ByteArrayInputStream(out.toByteArray());
		
		MimeMessage mimemessage=mailsender.createMimeMessage();
		try {
			MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimemessage,true);
			mimeMessageHelper.setFrom("pratap831@gmail.com");
			mimeMessageHelper.setTo(customer.getCustomerEmail());
			mimeMessageHelper.setSubject("Regarding GCappps family loan sanction letter");
			mimeMessageHelper.setText("Dear Customer,We have received your loan application and are happy to let you know that it has been granted.Disbursement will take place 24 hours after you sign the necessary documents.Please review the attached forms for more details. If you have other inquiries or clarifications, feel free to call or drop by GCapps office.");
			
//			ByteArrayResource bytearray=new ByteArrayResource(byt);
//		 
			byte[] bytearray=byt.readAllBytes();
			mimeMessageHelper.addAttachment("SanctionLetter.pdf",new ByteArrayResource(bytearray));
			
			mailsender.send(mimemessage);
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return san;
	}

}
