package com.elogroup.dto.status;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.elogroup.domain.StatusLead;
import com.elogroup.domain.User;
import com.elogroup.domain.enums.Role;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class StatusSavedto {
	@NotBlank
	private String description;
	
	
	public StatusLead transformToStatus () {
		StatusLead status = new StatusLead(null,this.description,null);
		return status;
	}
}
