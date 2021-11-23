package com.elogroup.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.elogroup.domain.User;
import com.elogroup.exeception.NotFoundException;
import com.elogroup.repository.UserRepository;
import com.elogroup.util.HashUtil;

@Service
public class UserService implements UserDetailsService {

	@Autowired private UserRepository userRepository;
	
	public User save(User user) {
		String hash = HashUtil.getSecureHash(user.getPassword());
		user.setPassword(hash);
		User createdUser = userRepository.save(user);
		return createdUser;
		
	}
	public User update(User user) {
		String hash = HashUtil.getSecureHash(user.getPassword());
		user.setPassword(hash);
		User updatedUser = userRepository.save(user);
		return updatedUser;
	}
	
	public User getById(Long id) {
		
		Optional<User> result = userRepository.findById(id);
		return result.orElseThrow(()-> new NotFoundException("Não há usuario com o id "+id));
	}
	public List<User> listAll(){
		List<User> users = userRepository.findAll();
		return users;
	}
	
	public User login(String username, String password) {
		password = HashUtil.getSecureHash(password);
		
		Optional<User> result = userRepository.login(username, password);
		return result.get();
	}
	
	public int updateRole(User user) {
		int affectedRows = userRepository.updateRole(user.getId(), user.getRole());
		return affectedRows;
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> result = userRepository.findByUsername(username);
		System.out.println("teste");
		if(!result.isPresent()) throw new UsernameNotFoundException("Usuário inexistente");
		System.out.println("teste2");
		User user = result.get();
		
		List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_"+user.getRole().name()));
		org.springframework.security.core.userdetails.User userSpring = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),authorities);
		return userSpring;
	}
	
}
