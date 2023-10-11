package com.GCappps.loanFin.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.GCappps.loanFin.app.model.EnquiryDetails;

@Repository
public interface EnquiryRepository extends JpaRepository<EnquiryDetails, String> {

	public  List<EnquiryDetails> findAllByEnquiryStatus(String enquiryStatus);

	public Optional<EnquiryDetails> findById(String enquieryId);

	public Optional<EnquiryDetails> findByPancardNumber(String pancardNumber);

}
