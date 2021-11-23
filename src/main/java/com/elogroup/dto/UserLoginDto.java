package com.elogroup.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class UserLoginDto {
	@NotNull(message = "Usuário obrigatório")
	private String username;
	
	@NotNull(message = "Senha obrigatória")
	private String password;
}
