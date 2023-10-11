package com.GCappps.loanFin.app.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GCappps.loanFin.app.model.Customer;
import com.GCappps.loanFin.app.repository.CustomerRepository;
import com.GCappps.loanFin.app.serviceI.CustomerServiceI;

@Service
public class CustomerServiceImpl implements CustomerServiceI {

	@Autowired
	CustomerRepository customerRepository;
	
	
	@Override
	public List<Customer> getCustomer(String customerVerificationStatus) {
		
		
		
		return customerRepository.findAllByCustomerVerificationStatus(customerVerificationStatus);
	}


	@Override
	public Customer saveCustomer(Customer customer) {
		
		return customerRepository.save(customer);
	}


	@Override
	public Optional<Customer> getOneCustomer(String customerId) {
		
		return customerRepository.findById(customerId);
	}


	@Override
	public Customer updateCustomer(Customer customerRead) {
		
		return customerRepository.save(customerRead);
	}

	
	@Override
	public Customer withoutDoc(Customer customer) {
		
		return customerRepository.save(customer);
	}


}
