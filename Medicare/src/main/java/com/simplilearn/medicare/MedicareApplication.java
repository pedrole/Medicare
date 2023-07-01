package com.simplilearn.medicare;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.simplilearn.medicare.pojo.Role;
import com.simplilearn.medicare.pojo.User;
import com.simplilearn.medicare.repo.RoleRepository;
import com.simplilearn.medicare.repo.UserRepo;
import com.simplilearn.medicare.service.RoleService;
import com.simplilearn.medicare.service.UserService;

@SpringBootApplication
public class MedicareApplication implements  CommandLineRunner {

	@Autowired UserService userService; 
	@Autowired RoleService roleService;
	@Autowired private PasswordEncoder passwordEncoder;
	public static void main(String[] args) {
		SpringApplication.run(MedicareApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		
		if(roleService.count()== 0) {
			
			Role adminRole = new Role(0, "ROLE_ADMIN");
			Role customerRole = new Role(0, "ROLE_CUSTOMER");
			
			roleService.insert(adminRole);
			roleService.insert(customerRole);
			
		}
		
		
		if(userService.count()==0) {
			
			User user = new User();
			user.setName("Administrator");
			user.setEmail("admin@mail.com");
			user.setPassword("admin");
			Role role = roleService.findByName("ROLE_ADMIN").get();
			Set roles = new HashSet<>();
			roles.add(role);
			//user.addRole(role);
			user.setRoles(roles);
			

			userService.addUser(user);
			
		}
		
	}

}
