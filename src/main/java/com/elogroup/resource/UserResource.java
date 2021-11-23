package com.elogroup.resource;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elogroup.domain.User;
import com.elogroup.dto.UserLoginDto;
import com.elogroup.dto.UserLoginResponsedto;
import com.elogroup.dto.UserSavedto;
import com.elogroup.dto.UserUpdateRoledto;
import com.elogroup.dto.UserUpdatedto;
import com.elogroup.security.JwtManager;
import com.elogroup.service.UserService;

@RestController
@RequestMapping(value = "users")
@CrossOrigin("http://localhost:4200")
public class UserResource {

	@Autowired private UserService userService;
	@Autowired private AuthenticationManager authManager;
	@Autowired private JwtManager jwtManager;

	@PostMapping
	public ResponseEntity<User> save(@RequestBody @Valid UserSavedto userdto){
		User userToSave = userdto.transformToUser();
		User createdUser = userService.save(userToSave);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> update (@RequestBody @Valid UserUpdatedto userdto,
			@PathVariable(name="id") Long id ){
		User user = userdto.transformToUser();
		user.setId(id);
		User updatedUser = userService.update(user);
		return ResponseEntity.ok(updatedUser);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getById (@PathVariable(name="id") Long id){
		User user = userService.getById(id);
		return ResponseEntity.ok(user);
	}
	
	@GetMapping
	public ResponseEntity<List<User>> listAll(){
		List<User> users = userService.listAll();
		return ResponseEntity.ok(users);
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserLoginResponsedto> login(@RequestBody @Valid UserLoginDto user){

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
		Authentication auth = authManager.authenticate(token);
		SecurityContextHolder.getContext().setAuthentication(auth);
		org.springframework.security.core.userdetails.User userSpring = 
				(org.springframework.security.core.userdetails.User) auth.getPrincipal();

		String username = userSpring.getUsername();
		List<String> roles = userSpring.getAuthorities().stream()
				.map(authority -> authority.getAuthority())
				.collect(Collectors.toList());
		UserLoginResponsedto jwt = jwtManager.createToken(username, roles);
		return ResponseEntity.ok(jwt);
	}
	
	@PatchMapping("/role/{id}")
	public ResponseEntity<?> updateRole(@PathVariable(name="id") Long id,
			@RequestBody UserUpdateRoledto userdto){
		User user = new User();
		user.setId(id);
		user.setRole(userdto.getRole());
		
		userService.updateRole(user);
		return ResponseEntity.ok().build();
	}
	
	
	
}
