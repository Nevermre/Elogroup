package com.elogroup.dto.lead;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.elogroup.domain.Lead;
import com.elogroup.domain.Opportunity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class LeadSavedto {

	
	
	@NotBlank
	private String customerName;
	
	@NotBlank
	private String customerPhone;

	@NotBlank
	private String customerEmail;
	
	@NotNull
	private List<String> opportunities;
	
	public Lead transformToLead() {
		Lead lead = new Lead(null, null,this.customerName,this.customerPhone,this.customerEmail,null,null,null);
		return lead;
	}
}
