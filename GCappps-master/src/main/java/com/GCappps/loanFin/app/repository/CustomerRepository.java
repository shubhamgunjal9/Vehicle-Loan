package com.GCappps.loanFin.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.GCappps.loanFin.app.model.Customer;
import com.GCappps.loanFin.app.model.Ledger;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,String> {


public	List<Customer> findAllByCustomerVerificationStatus(String customerVerificationStatus);

public Optional<Customer> findByCustomerId(String customerId);

public Optional<Customer> findByLedger(Ledger ledger);

}
