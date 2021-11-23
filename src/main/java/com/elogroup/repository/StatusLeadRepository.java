package com.elogroup.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.elogroup.domain.StatusLead;

@Repository
public interface StatusLeadRepository extends JpaRepository<StatusLead, Long>  {
	@Query("SELECT d FROM StatusLead as d where d.description =?1 ")
	public Optional<StatusLead> buscarPorDescricao(String description);
}
