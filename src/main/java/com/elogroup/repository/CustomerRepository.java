package com.elogroup.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elogroup.domain.Customer;
import com.elogroup.domain.Lead;
@Repository
public interface CustomerRepository  extends JpaRepository<Customer, Long> {
	List<Customer> findByLead(Lead lead);
	
}
