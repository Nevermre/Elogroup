package com.elogroup.resource;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elogroup.domain.Customer;
import com.elogroup.domain.Lead;
import com.elogroup.domain.Opportunity;
import com.elogroup.domain.StatusLead;
import com.elogroup.domain.User;
import com.elogroup.dto.UserUpdatedto;
import com.elogroup.dto.lead.LeadSavedto;
import com.elogroup.dto.lead.LeadUpdatedto;
import com.elogroup.dto.status.StatusSavedto;
import com.elogroup.service.CustomerService;
import com.elogroup.service.LeadService;
import com.elogroup.service.OpportunityService;
import com.elogroup.service.StatusLeadService;

@RestController
@RequestMapping(value = "lead")
@CrossOrigin("http://localhost:4200")
public class LeadResource {
	
	@Autowired LeadService leadService;
	@Autowired CustomerService customerService;
	@Autowired OpportunityService opportunityService;
	@PostMapping
	public ResponseEntity<Lead> save(@RequestBody @Valid LeadSavedto leadto){
		
		
		
		Lead leadToSave = leadto.transformToLead();
		Lead createdLead = leadService.save(leadToSave);
		List<String> opportunities = leadto.getOpportunities();
		List<Opportunity> Opportunity = new ArrayList<Opportunity>();
		if(opportunities.size() != 0) {
			opportunities.forEach(opportunity ->{
				Opportunity op = new Opportunity();
				op.setDescription(opportunity);
				op.setLeads(createdLead);
				opportunityService.save(op);
			});
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(createdLead);
	}
	@PutMapping("/{id}")
	public ResponseEntity<Lead> update (@RequestBody @Valid LeadUpdatedto leadto,
			@PathVariable(name="id") Long id ){
		
		Lead lead = leadto.transformToLead();
		lead.setId(id);
		Lead updatedLead = leadService.update(lead);
		if(updatedLead.getStatus().getId() == 3L) {
			List<Customer> exists = customerService.findByLead(lead);
			if(exists.size() == 0) {
				Customer customer = new Customer();
				customer.setLead(updatedLead);
				customerService.save(customer);
			}
		}
		return ResponseEntity.ok(updatedLead);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Lead> getById (@PathVariable(name="id") Long id){
		Lead lead = leadService.getById(id);
		return ResponseEntity.ok(lead);
	}
	
	@GetMapping
	public ResponseEntity<List<Lead>> listAll(){
		List<Lead> lead = leadService.listAll();
		return ResponseEntity.ok(lead);
	}
	
}
