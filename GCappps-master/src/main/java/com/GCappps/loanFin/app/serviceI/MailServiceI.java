package com.GCappps.loanFin.app.serviceI;

import com.GCappps.loanFin.app.model.Emailattach;
import com.GCappps.loanFin.app.model.EnquiryDetails;
import com.GCappps.loanFin.app.model.SimpleMail;

public interface MailServiceI {

	public void sendmail(SimpleMail mail);

	public void sendattachment(Emailattach mail, String filename);

	public void sendcibilstatus(EnquiryDetails enquiryDetails);

}
