package com.simplilearn.medicare.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplilearn.medicare.pojo.Role;
import com.simplilearn.medicare.repo.RoleRepository;

@Service
public class RoleService {
	@Autowired
	private RoleRepository repo;

	
	public Role addRole(Role role) {
		return repo.save(role);
		
	}
	
	public long count() {
		return repo.count();
	}
	public Role insert(Role role) {
		return repo.save(role);
	}
	
	public Optional<Role> findByName(String name) {
		Optional<Role> role = repo.findByName(name);
		return role;
	}
}
