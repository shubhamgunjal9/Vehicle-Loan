package com.GCappps.loanFin.app.serviceImpl;

import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.hibernate.annotations.CreationTimestamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.GCappps.loanFin.app.enums.CustomerEnum;
import com.GCappps.loanFin.app.enums.InstallmentEnum;
import com.GCappps.loanFin.app.enums.LedgerLoanStatusEnum;
import com.GCappps.loanFin.app.model.Customer;
import com.GCappps.loanFin.app.model.Installment;
import com.GCappps.loanFin.app.model.Ledger;
import com.GCappps.loanFin.app.repository.CustomerRepository;
import com.GCappps.loanFin.app.repository.LedgerRepository;
import com.GCappps.loanFin.app.serviceI.LedgerServiceI;
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
public class LedgerServiceImpl implements LedgerServiceI{
	 
	
	@Autowired
	LedgerRepository ledRepo;
	
	@Autowired
	Ledger ledger;
	
	@Autowired
	CustomerRepository cust;
	
	@Autowired
	JavaMailSender mailsender;

	@Override
	public Ledger ledgergeneration(Customer customer) {
		int installmentid=ThreadLocalRandom.current().nextInt(999,9999);
		ledger.setLedgerId("CGappps_Ledger-"+installmentid);
		Calendar date = new GregorianCalendar();
		int year = date.get(Calendar.YEAR);  
		int month = date.get(Calendar.MONTH);   
		int day = date.get(Calendar.DAY_OF_MONTH);  
		ledger.setLedgerCreatedDate(day+"/"+(month+1)+"/"+year);
		ledger.setTotalLoanAmount(customer.getSanctionLetter().getLoanAmountWithInterest());
		ledger.setTenure(customer.getSanctionLetter().getLoanTenure());
		ledger.setMonthlyEMI(customer.getSanctionLetter().getMonthlyEmiAmount());
		ledger.setAmountPaidTillDate(null);
		ledger.setRemainingAmount(customer.getSanctionLetter().getLoanAmountWithInterest());
		ledger.setDefaulterCount(null);
		int lastmonth=customer.getSanctionLetter().getLoanTenure();
		
		List<Installment> l=new ArrayList<>();
		String lastdate=null;
		for(int i=1;i<=lastmonth;i++) {
			Installment install=new Installment();
			int x=(i+(month+1));
			if(x<=12) {
				 
				install.setInstallmentNumber(x-1);
				Month monthofinstallment= Month.of(x);
				install.setInstallmentMonth(monthofinstallment+","+year);
				install.setPaymentStatus(String.valueOf(InstallmentEnum.NA));
//				install.setInstallmentMonth(String.valueOf(InstallmentEnum.NA));
				lastdate=monthofinstallment+","+year;
			}
			else if(x>12 && x<24) {
				int m=(x)%12;
				 
				install.setInstallmentNumber(x-1);
				Month monthofinstallment= Month.of(m);
				install.setInstallmentMonth(monthofinstallment+","+(year+1));
				lastdate=monthofinstallment+","+(year+1);
				install.setPaymentStatus(String.valueOf(InstallmentEnum.NA));
				//install.setInstallmentMonth(String.valueOf(InstallmentEnum.NA));
			}
			else if(x==24) {
				 
				install.setInstallmentNumber(x-1);
				Month monthofinstallment= Month.of(12);
				install.setInstallmentMonth(monthofinstallment+","+(year+1));
				lastdate=monthofinstallment+","+(year+2);
				install.setPaymentStatus(String.valueOf(InstallmentEnum.NA));
			}
			else if((x)>24 && (x)<36) {
				int m=(x)%12;
				 
				install.setInstallmentNumber(x-1);
				Month monthofinstallment= Month.of(m);
				install.setInstallmentMonth(monthofinstallment+","+(year+2));
				lastdate=monthofinstallment+","+(year+2);
				install.setPaymentStatus(String.valueOf(InstallmentEnum.NA));
				 
			}
			else if(x==36) {
				 
				install.setInstallmentNumber(x-1);
				Month monthofinstallment= Month.of(12);
				install.setInstallmentMonth(monthofinstallment+","+(year+2));
				lastdate=monthofinstallment+","+(year+2);
				install.setPaymentStatus(String.valueOf(InstallmentEnum.NA));
				 
			}
			else if((x)>36 && (x)<48) {
				int m=(x)%12;
				install.setInstallmentNumber(x-1);
				Month monthofinstallment= Month.of(m);
				install.setInstallmentMonth(monthofinstallment+","+(year+3));
				lastdate=monthofinstallment+","+(year+3);
				install.setPaymentStatus(String.valueOf(InstallmentEnum.NA));
			}
			
			l.add(install);
			
			
		}
		ledger.setInstallments(l);
		ledger.setLoanEndDate(lastdate);
		ledger.setLoanStatus(String.valueOf(LedgerLoanStatusEnum.Standard));
		customer.setLedger(ledger);
		customer.setCustomerVerificationStatus(toString().valueOf(CustomerEnum.Ledger_Generated));
		cust.save(customer);		
		return ledRepo.save(ledger);
	}

	@Override
	public Ledger payinstallment(Ledger ledger, Integer installmentnumber) {
		Optional<Customer> custo=cust.findByLedger(ledger);
		Customer customer=custo.get();
		Double emi=ledger.getMonthlyEMI();
		List<Installment> list=ledger.getInstallments();
		List<Installment> installmentlist=new ArrayList<>();
		int count=0;
		for(Installment l:list) {
			if(l.getInstallmentNumber()==installmentnumber) {
				l.setPaymentStatus(String.valueOf(InstallmentEnum.Paid));
				Date date=new Date();
				l.setInstallementPaidDate(date);
				SimpleMailMessage message=new SimpleMailMessage();
				message.setFrom("pratap831@gmail.com");
				message.setTo(customer.getCustomerEmail());
				message.setSubject("Regarding GCappps Paid installment");
				message.setText("Respected Customer,"+customer.getCustomerFirstName()+" "+customer.getCustomerLastName()+ "\n"+
						"Your installment no "+l.getInstallmentNumber()+" is successfully paid.\n"
						+"Ledger id="+ledger.getLedgerId()+"\n"
						+"Detail information about transaction is:\n"
						+"Installment ID="+l.getInstallmentId()+"\n"
						+"Installment Number="+l.getInstallmentNumber()+"\n"
						+"Month of Installment="+l.getInstallmentMonth()+"\n"
						+"Date of payment="+l.getInstallementPaidDate()+"\n"
						+"\n"
						+"\n"
						+ "Thanking You For Banking With Us, \n"
						+ "GCappps FinTech.");
				 
				message.setText(message.getText());
				mailsender.send(message);
			}
			installmentlist.add(l);
			ledger.setInstallments(list);
		}
		int x=0;
		for(Installment l:installmentlist)	{
		try {
			if(l.getPaymentStatus().equals(String.valueOf(InstallmentEnum.UnPaid))) {
				x++;
			}
			if(l.getPaymentStatus().equals(String.valueOf(InstallmentEnum.Paid))) {
				count++;
				if((count+x)==ledger.getTenure()) 
				{
					ledger.setLoanStatus(String.valueOf(LedgerLoanStatusEnum.Loan_Cleared));
					System.err.println(ledger.getLoanStatus());
					SimpleMailMessage message=new SimpleMailMessage();
					message.setFrom("pratap831@gmail.com");
					message.setTo(customer.getCustomerEmail());
					message.setSubject("Regarding GCappps loan Clearance");
					message.setText("Respected Customer,"+customer.getCustomerFirstName()+" "+customer.getCustomerLastName()+ "\n"+
							"We GCappps happy to announce that after successful completion of your loan tenure your loan account is cleared."
							+ "We except your further cooperation with GCappps FinTech. \n"
							+"\n"
							+"\n"
							+ "Thanking You For Banking With Us, \n"
							+ "GCappps FinTech.");
					 
					mailsender.send(message);
				}
			}}
			catch(NullPointerException e)
			{
				e.printStackTrace();
			}
			
		}	
		Double paidinstallment=(emi*count);
		ledger.setAmountPaidTillDate(paidinstallment);
		Double remainamount=ledger.getTotalLoanAmount()-paidinstallment;
		ledger.setRemainingAmount(remainamount);
		return ledRepo.save(ledger);
	}
	
	
	
	@Override
	public Ledger unpayinstallment(Ledger ledger, Integer installmentnumber) {
		Optional<Customer> custo=cust.findByLedger(ledger);
		Customer customer=custo.get();
		int addInstallment=ledger.getTenure();
		List<Installment> list=ledger.getInstallments();
		Installment i=new Installment();
		int count=0;
		int x=0;
		for(Installment l:list) {
			if(l.getInstallmentNumber()==installmentnumber) {
				
				l.setPaymentStatus(String.valueOf(InstallmentEnum.UnPaid));
				Date date=new Date();
				l.setInstallementPaidDate(date);
				ledger.setTenure(addInstallment+1);
				x++;
				SimpleMailMessage message=new SimpleMailMessage();
				message.setFrom("pratap831@gmail.com");
				message.setTo(customer.getCustomerEmail());
				message.setSubject("Regarding GCappps UnPaid installment");
				message.setText("Respected Customer,"+customer.getCustomerFirstName()+" "+customer.getCustomerLastName()+ "\n"+
						"Your installment no "+l.getInstallmentNumber()+" is Unpaid.Please pay installment as soon as possible to avoid further action.\n"
						+"Ledger id="+ledger.getLedgerId()+"\n"
						+"Detail information about transaction is:\n"
						+"You missed installment Number ="+l.getInstallmentNumber()+"\n"
						+"Your missed month of Installment is="+l.getInstallmentMonth()+"\n"
						+"\n" 
						+"\n"
						+"\n"
						+ "Thanking You For Banking With Us, \n"
						+ "GCappps FinTech.");
				mailsender.send(message);
			
				 
			}
		}
		if(x==1) {
			i.setPaymentStatus(String.valueOf(InstallmentEnum.NA));
			i.setInstallmentMonth(String.valueOf(InstallmentEnum.NA));
			i.setInstallmentNumber(addInstallment+1);
			i.setInstallmentMonth(String.valueOf(InstallmentEnum.NA));
			list.add(list.size(),i );
		}
		int n=0;
		for(Installment l:list)	{
			try {
//				n=0;
				if(l.getPaymentStatus().equals(String.valueOf(InstallmentEnum.UnPaid))) {
					count++;
					n++;
					 
				}
				else {
					n=0;
				}
				if(n==3 && n<6) {
					ledger.setLoanStatus(String.valueOf(LedgerLoanStatusEnum.Non_Performing_Asset));
				}
				if(n==6) {
					System.err.println(n);
					ledger.setLoanStatus(String.valueOf(LedgerLoanStatusEnum.Bad_Loan));
				}
				
				
			}
				catch(NullPointerException e)
				{
					e.printStackTrace();
				}
				
			}
		 
		ledger.setDefaulterCount(count);
		return ledRepo.save(ledger);
	}

	@Override
	public Optional<Ledger> getLedger(Customer customer) {
		// TODO Auto-generated method stub
		return ledRepo.findById(customer.getLedger().getLedgerId());
	}

}
