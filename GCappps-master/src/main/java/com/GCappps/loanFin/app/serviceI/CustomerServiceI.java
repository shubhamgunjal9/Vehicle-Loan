package com.GCappps.loanFin.app.serviceI;

import java.util.List;
import java.util.Optional;

import com.GCappps.loanFin.app.model.Customer;

public interface CustomerServiceI {

public	Customer saveCustomer(Customer customer);

public List<Customer> getCustomer(String customerVerificationStatus);

public Optional<Customer> getOneCustomer(String customerId);

public Customer updateCustomer(Customer customerRead);

public Customer withoutDoc(Customer customer);

}
