package com.elogroup.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elogroup.domain.StatusLead;
import com.elogroup.domain.User;
import com.elogroup.exeception.NotFoundException;
import com.elogroup.repository.StatusLeadRepository;
import com.elogroup.util.HashUtil;

@Service

public class StatusLeadService {

	@Autowired private StatusLeadRepository statusLeadRepository;
	
	public StatusLead save(StatusLead status) {
		StatusLead newStatus = statusLeadRepository.save(status);
		return newStatus;
	}
	
	public StatusLead update(StatusLead status) {
		StatusLead updatedStatus = statusLeadRepository.save(status);
		return updatedStatus;
	}
	
	public StatusLead getById(Long id) {
		Optional<StatusLead> result = statusLeadRepository.findById(id);
		return result.orElseThrow(()-> new NotFoundException("Não há usuario com o id "+id));
	}
	
	public List<StatusLead> listAll(){
		List<StatusLead> status = statusLeadRepository.findAll();
		return status;
	}
	
}
