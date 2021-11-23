package com.elogroup.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elogroup.domain.Customer;
import com.elogroup.domain.Lead;
import com.elogroup.exeception.NotFoundException;
import com.elogroup.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired private CustomerRepository customerRepository;
	
	public Customer save(Customer customer) {
		Customer newCustomer = customerRepository.save(customer);
		return newCustomer;
	}
	
	public Customer update(Customer customer) {
		Customer updatedCustomer = customerRepository.save(customer);
		return updatedCustomer;
	}
	
	public Customer getById(Long id) {
		Optional<Customer> result = customerRepository.findById(id);
		return result.orElseThrow(()-> new NotFoundException("Não há usuario com o id "+id));
	}
	
	public List<Customer> findByLead(Lead lead) {
		
		
		List<Customer> result = customerRepository.findByLead(lead);
		return result;
	}
	
	public List<Customer> listAll(){
		List<Customer> Customer = customerRepository.findAll();
		return Customer;
	}
}
