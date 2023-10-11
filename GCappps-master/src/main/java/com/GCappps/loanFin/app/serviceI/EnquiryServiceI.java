package com.GCappps.loanFin.app.serviceI;

import java.util.List;
import java.util.Optional;

import com.GCappps.loanFin.app.model.EnquiryDetails;

public interface EnquiryServiceI {

	public EnquiryDetails customerEnquiry(EnquiryDetails enquiryDetails);

	public Optional<EnquiryDetails> customerLogin(String enquiryId);

	public List<EnquiryDetails> customerEnquiries(String enquiryStatus);

	public Optional<EnquiryDetails> cibilScoreCheck(String enquieryId);

	public EnquiryDetails updateEnquiry(EnquiryDetails enquiryDetails);

	public EnquiryDetails saveCibilData(EnquiryDetails enqDetails);

	public Optional<EnquiryDetails> getEnquiryByPan(String pancardNumber);

}
