package com.GCappps.loanFin.app.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.GCappps.loanFin.app.enums.EnquiryStatus;
import com.GCappps.loanFin.app.model.Cibil;
import com.GCappps.loanFin.app.model.EnquiryDetails;
import com.GCappps.loanFin.app.repository.EnquiryRepository;
import com.GCappps.loanFin.app.serviceI.EnquiryServiceI;

@Service
public class EnquiryServiceImpl implements EnquiryServiceI {

	@Autowired
	EnquiryRepository enquiryRepository;

	public EnquiryDetails customerEnquiry(EnquiryDetails enquiryDetails) {
		//Random EnquiryId between 100 to 200

		enquiryDetails.setEnquiryId("GCappps-Enq-" +ThreadLocalRandom.current().nextInt(100, 200));
		enquiryDetails.setEnquiryStatus(String.valueOf(EnquiryStatus.Enquired));
	EnquiryDetails enquiryDetails2 = enquiryRepository.save(enquiryDetails);
		return enquiryDetails2;
	}

	public Optional<EnquiryDetails> customerLogin(String enquiryId) {

		return enquiryRepository.findById(enquiryId);
	}

	@Override
	public List<EnquiryDetails> customerEnquiries(String enquiryStatus) {

		return enquiryRepository.findAllByEnquiryStatus(enquiryStatus);
	}

	@Override
	public Optional<EnquiryDetails> cibilScoreCheck(String enquieryId) {

		return enquiryRepository.findById(enquieryId);
	}

	public EnquiryDetails updateEnquiry(EnquiryDetails enquiryDetails) {
		

		enquiryDetails.setEnquiryStatus(String.valueOf(EnquiryStatus.Cibilok));

		EnquiryDetails enquiryDetails2 = enquiryRepository.save(enquiryDetails);
		return enquiryDetails2;
	}

	@Override
	public EnquiryDetails saveCibilData(EnquiryDetails enqDetails) {
		

		if(enqDetails.getCibilScore()>=700) {
			enqDetails.setEnquiryStatus(String.valueOf(EnquiryStatus.Cibilok));
		}	
		else if(enqDetails.getCibilScore()<=750) {
			enqDetails.setEnquiryStatus(String.valueOf(EnquiryStatus.Cibilreject));
			
		}
		 return enquiryRepository.save(enqDetails);
	}

	@Override
	public Optional<EnquiryDetails> getEnquiryByPan(String pancardNumber) {
		// TODO Auto-generated method stub
		return enquiryRepository.findByPancardNumber(pancardNumber);
	}

}
