package com.elogroup.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.elogroup.domain.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 75, nullable = false,unique = true)
	private String username;

	@Getter(onMethod = @__({@JsonIgnore}))
	@Setter(onMethod = @__({@JsonProperty}))
	@Column(length = 75, nullable = false)
	private String Password;
	
	@Column(length = 20,nullable = false,updatable = false)
	@Enumerated(EnumType.STRING)
	private Role role;

	
	
	
}
