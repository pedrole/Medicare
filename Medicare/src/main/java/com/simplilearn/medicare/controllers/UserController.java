package com.simplilearn.medicare.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simplilearn.medicare.pojo.Role;
import com.simplilearn.medicare.pojo.User;
import com.simplilearn.medicare.service.RoleService;
import com.simplilearn.medicare.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService service;
	@Autowired
	private RoleService roleService;

	
	@PostMapping("/")
	 
	public ResponseEntity<User> adduser(@RequestBody User u) {

		Role role = roleService.findByName("ROLE_CUSTOMER").get();
		u.addRole(role);
		
		User user = service.createUser(u);
		 
		
	
		

		if (user != null)
			return new ResponseEntity<User>(user, HttpStatus.CREATED);
		else
			return new ResponseEntity<User>(user, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@GetMapping("/")
	public List<User> getAllUser() {
		return service.getAllUser();
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable int id) {
		User user = service.getUserById(id);

		if (user != null)
			return new ResponseEntity<User>(user, HttpStatus.OK);
		else
			return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateUser(@PathVariable int id, @RequestBody User newUser) {
		User user = service.updateUser(id, newUser);

		if (user != null)
			return new ResponseEntity<Object>(user, HttpStatus.OK);
		else
			return new ResponseEntity<Object>("No User Available to Update", HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable int id) {
		boolean result = service.deleteUser(id);
		if (result)
			return new ResponseEntity<String>("Object Deleted", HttpStatus.OK);
		else
			return new ResponseEntity<String>("NO user Found", HttpStatus.NOT_FOUND);

	}

}
