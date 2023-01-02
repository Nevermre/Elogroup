package com.elogroup.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elogroup.domain.Lead;
import com.elogroup.domain.Opportunity;
import com.elogroup.exeception.NotFoundException;
import com.elogroup.repository.LeadRepository;
import com.elogroup.repository.OpportunityRepository;
import com.elogroup.repository.StatusLeadRepository;

@Service
public class LeadService {
	
	@Autowired LeadRepository leadRepository;
	@Autowired StatusLeadRepository statusLeadRepository;
	public Lead save(Lead lead) {
		lead.setStatus(statusLeadRepository.getOne(1L));
		lead.setDate(new Date());
		Lead newLead = leadRepository.save(lead);
		return newLead;
	}
	
	public Lead update(Lead lead) {
		
		Lead updatedLead = leadRepository.save(lead);
		return updatedLead;
	}
	
	public Lead getById(Long id) {
		Optional<Lead> result = leadRepository.findById(id);
		return result.orElseThrow(()-> new NotFoundException("Não há usuario com o id "+id));
	}
	
	public List<Lead> listAll(){
		List<Lead> Lead = leadRepository.findAll();
		return Lead;
	}
	
}
