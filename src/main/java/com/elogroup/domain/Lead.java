package com.elogroup.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.elogroup.domain.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity(name="leadz")
public class Lead implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false,updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	@Column(length = 75, nullable = false)
	private String customername;
	
	@Column(length = 75, nullable = false)
	private String customerphone;
	
	@Column(length = 75, nullable = false)
	private String customeremail;
	
	//@Getter(onMethod = @__({@JsonIgnore}))

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_id",nullable = false)
	private StatusLead status;
	
	@Getter(onMethod = @__({@JsonIgnore}))

	@OneToMany(mappedBy = "leads")
	@PrimaryKeyJoinColumn
	private List<Opportunity> opportunity = new ArrayList<Opportunity>();
	
	@Getter(onMethod = @__({@JsonIgnore}))
	@OneToOne(mappedBy = "lead", cascade = CascadeType.MERGE)
    @PrimaryKeyJoinColumn

	private Customer customer;
}
