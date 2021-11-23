package com.elogroup.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.elogroup.domain.User;
import com.elogroup.domain.enums.Role;


@SpringBootTest
public class UserRepositoryTests {
	@Autowired private UserRepository userRepository; 
	
	@Disabled
	@Order(1)
	public void saveTest() {
		User user = new User(null,"lucas.peres","1234",Role.ADMINISTRATOR);
		User createdUser = userRepository.save(user);
		assertThat(createdUser.getId()).isEqualTo(1L);
	}
	@Disabled

	@Order(2)
	public void updateTest() {
		User user = new User(1L,"lucas.peres2","1234",Role.ADMINISTRATOR);
		User updatedUser = userRepository.save(user);
		assertThat(updatedUser.getUsername()).isEqualTo("lucas.peres2");
	}
	@Disabled

	@Order(3)
	public void getByIdTest() {
		Optional<User> result = userRepository.findById(1L);
		User user = result.get();
		assertThat(user.getUsername()).isEqualTo("lucas.peres2");

	}
	@Disabled

	@Order(4)
	public void listTest() {
		List<User> users = userRepository.findAll();
		assertThat(users.size()).isEqualTo(1);

	}
	@Disabled

	@Order(5)
	public void loginTest() {
		Optional<User> result = userRepository.login("lucas.peres2", "1234");
		User loggedUser = result.get();
		assertThat(loggedUser.getId()).isEqualTo(1L);
	}
	@Test

	@Order(1)
	public void updateRoleTest() {
		
		int affectedRows = userRepository.updateRole(2L, Role.SIMPLE);
		assertThat(affectedRows).isEqualTo(1);
	}
}
