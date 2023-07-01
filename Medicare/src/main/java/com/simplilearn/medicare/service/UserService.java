package com.simplilearn.medicare.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.simplilearn.medicare.pojo.Role;
import com.simplilearn.medicare.pojo.User;
import com.simplilearn.medicare.repo.RoleRepository;
import com.simplilearn.medicare.repo.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo repo;
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public User addUser(User u) {
		u.setPassword(this.passwordEncoder.encode(u.getPassword()));
		return repo.save(u);
	}

	public List<User> getAllUser() {
		return repo.findAll();
	}

	public User getUserById(int id) {
		if (repo.findById(id).isPresent()) {
			return repo.findById(id).get();
		} else {
			return null;
		}

	}
	public long count() {
		return repo.count();
	}
	public User insert(User user) {
		return repo.save(user);
	}

	//register a new user
	public User createUser(User user){
		User newUser = this.repo.findByEmail(user.getUsername()).orElse(null);
		
		//if user exists or not
		try {
			if(newUser!=null) {
				throw new Exception("Username already exists!");
			}else {
				//create new user
				
				//saving roles
				
				
				//encoding password
				user.setPassword(this.passwordEncoder.encode(user.getPassword()));
				
				newUser = this.repo.save(user);
				
			}
		} catch (Exception e) {
			System.out.println("User is already created!");
			System.out.println(e);
		}
		
		return newUser;
	}

	public User updateUser(int id, User newUser) {
		if (repo.findById(id).isPresent()) {
			User olduser = repo.findById(id).get();
			olduser.setName(newUser.getName());
			olduser.setEmail(newUser.getEmail());
			olduser.setPassword(newUser.getPassword());
			olduser.setCountry(newUser.getCountry());
			olduser.setLogin(newUser.getLogin());

			return repo.save(olduser);
		} else {
			return null;
		}
	}

	public boolean deleteUser(int id) {
		if (repo.findById(id).isPresent()) {
			repo.deleteById(id);
			return true;
		}

		else {
			return false;
		}
	}

}