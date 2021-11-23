package com.elogroup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elogroup.domain.Lead;

@Repository

public interface LeadRepository  extends JpaRepository<Lead, Long>{

}
