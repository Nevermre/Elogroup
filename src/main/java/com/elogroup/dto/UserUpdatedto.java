package com.elogroup.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.elogroup.domain.User;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserUpdatedto {
	@NotBlank
	private String username;
	@Size(min = 3, max = 74, message="Senha deve ter entre 3 e 74 caracteres" )
	private String password;

	public User transformToUser () {
		User user = new User(null,this.username,this.password,
				null);
		return user;
	}
}
