package com.GCappps.loanFin.app.serviceImpl;

import java.io.ByteArrayInputStream;
import java.util.Date;
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
import com.GCappps.loanFin.app.model.LoanDisbursement;
import com.GCappps.loanFin.app.repository.CustomerRepository;
import com.GCappps.loanFin.app.repository.LoanDisbursementsRepository;
import com.GCappps.loanFin.app.serviceI.LoanDisbursementserviceI;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class LoanDisbursementserviceImpl implements LoanDisbursementserviceI {
	@Autowired
	LoanDisbursement loan;

	@Autowired
	LoanDisbursementsRepository ldr;

	@Autowired
	CustomerRepository custrepo;

	@Autowired
	JavaMailSender mailsender;

	private Logger logger = LoggerFactory.getLogger(LoanDisbursementserviceImpl.class);

	@Override
	public LoanDisbursement loandisbursement(Customer customer) {
		loan.setLoanId("GCapps-LoanId-" + ThreadLocalRandom.current().nextInt(999, 9999));
		loan.setTotalAmount(customer.getSanctionLetter().getLoanAmountSanctioned());
		loan.setDealarAccountNumber((customer.getDealerData().getAccountNumber()));
		loan.setDealerIFSCCode(customer.getDealerData().getAccountIFSCCode());
		Date date = new Date();
		loan.setAmountPaidDate(date);

		customer.setLoanDisbursement(loan);
		customer.setCustomerVerificationStatus(String.valueOf(CustomerEnum.Loan_Disbursed));
		custrepo.save(customer);

		logger.info("Sanction Letter pdf started");
		String title = "GCapps-Get the key of happiness";
		String contet = "Mr" + customer.getDealerData().getDealerName();
		String content1 = "as per your vehicle quotation provided to " + customer.getCustomerFirstName() + " "
				+ customer.getCustomerLastName() + "and further loan application is approved of amount"
				+ customer.getSanctionLetter().getLoanAmountSanctioned()
				+ "is sanctioned by GCappps and amount is disbursed into your account" + " "
				+ customer.getDealerData().getAccountNumber();
		String content2 = "Thanks for your association with GCappps.Hope your experience is pleasant";

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Document document = new Document();

		PdfWriter.getInstance(document, out);
		document.open();

		Font titlefont = FontFactory.getFont(FontFactory.TIMES_BOLD,25);
		titlefont.setColor(186, 2, 237);
		Paragraph titlepara = new Paragraph(title, titlefont);
		titlepara.setAlignment(Element.ALIGN_CENTER);
		document.add(titlepara);
		Paragraph paracontent1 = new Paragraph(content1);
		document.add(paracontent1);
		Paragraph paracontent2 = new Paragraph(content2);
		document.add(paracontent2);

		document.close();
		ByteArrayInputStream byt = new ByteArrayInputStream(out.toByteArray());

		MimeMessage mimemessage = mailsender.createMimeMessage();
		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimemessage, true);
			mimeMessageHelper.setFrom("pratap831@gmail.com");
			mimeMessageHelper.setTo(customer.getDealerData().getDealerEmail());
			mimeMessageHelper.setSubject("Regarding GCappps family loan disbursement letter.");
			mimeMessageHelper.setText(
					"Dear Dealer,We have received your loan quotation letter and are happy to let you know that it has been granted.Disbursement will take place 24 hours after generatation of disbursement documents.Please review the attached letter for more details. If you have other inquiries or clarifications, feel free to call or drop by GCapps office.");

//			ByteArrayResource bytearray=new ByteArrayResource(byt);
//		 
			byte[] bytearray = byt.readAllBytes();
			mimeMessageHelper.addAttachment("LoanDisbursementLetter.pdf", new ByteArrayResource(bytearray));

			mailsender.send(mimemessage);

		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ldr.save(loan);
	}

}
