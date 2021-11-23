package com.elogroup.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elogroup.domain.User;
import com.elogroup.domain.enums.Role;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	@Query("SELECT u FROM User AS u WHERE user_name = ?1 and password = ?2")
	public Optional<User> login(String username, String password);
	
	@Transactional(readOnly = false)
	@Modifying
	@Query("Update User set role=?2 where id = ?1")
	public int updateRole(Long id, Role role);
	
	public Optional<User> findByUsername(String userName);
	
}
