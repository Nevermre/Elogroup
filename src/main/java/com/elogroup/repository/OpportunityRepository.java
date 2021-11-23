package com.elogroup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elogroup.domain.Opportunity;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Long> {

}
