package com.elogroup.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elogroup.domain.Customer;
import com.elogroup.domain.Opportunity;
import com.elogroup.exeception.NotFoundException;
import com.elogroup.repository.OpportunityRepository;

@Service
public class OpportunityService {

	@Autowired OpportunityRepository opportunityRepository;
	
	public Opportunity save(Opportunity opportunity) {
		Opportunity newOpportunity = opportunityRepository.save(opportunity);
		return newOpportunity;
	}
	
	public Opportunity update(Opportunity opportunity) {
		Opportunity updatedOpportunity = opportunityRepository.save(opportunity);
		return updatedOpportunity;
	}
	
	public Opportunity getById(Long id) {
		Optional<Opportunity> result = opportunityRepository.findById(id);
		return result.orElseThrow(()-> new NotFoundException("Não há usuario com o id "+id));
	}
	
	public List<Opportunity> listAll(){
		List<Opportunity> Opportunity = opportunityRepository.findAll();
		return Opportunity;
	}
	
}
