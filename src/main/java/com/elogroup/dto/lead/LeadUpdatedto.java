package com.elogroup.dto.lead;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.elogroup.domain.Lead;
import com.elogroup.domain.StatusLead;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class LeadUpdatedto {
	
	
	@NotBlank
	private String customerName;
	
	@NotBlank
	private String customerPhone;

	@NotBlank
	private String customerEmail;
	
	@NotNull
	private StatusLead status = new StatusLead();

	
	public Lead transformToLead() {
		Lead lead = new Lead(null, null,this.customerName,this.customerPhone,this.customerEmail,this.status,null,null);
		return lead;
	}
}
