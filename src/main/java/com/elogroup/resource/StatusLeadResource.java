package com.elogroup.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elogroup.domain.StatusLead;
import com.elogroup.dto.status.StatusSavedto;
import com.elogroup.service.StatusLeadService;

@RestController
@RequestMapping(value = "status")
public class StatusLeadResource {

	@Autowired StatusLeadService statusLeadService;
	@PostMapping
	public ResponseEntity<StatusLead> save(@RequestBody @Valid StatusSavedto statusdto){
		StatusLead statusToSave = statusdto.transformToStatus();
		StatusLead createdStatus = statusLeadService.save(statusToSave);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdStatus);
	}
	
}
