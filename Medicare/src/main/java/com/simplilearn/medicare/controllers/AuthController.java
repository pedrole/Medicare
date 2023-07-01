package com.simplilearn.medicare.controllers;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simplilearn.medicare.jwt.JwtTokenUtil;
import com.simplilearn.medicare.pojo.AuthRequest;
import com.simplilearn.medicare.pojo.AuthResponse;
import com.simplilearn.medicare.pojo.User;
import com.simplilearn.medicare.service.UserDetailService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
	
	@Autowired AuthenticationManager authenticationManager;
	@Autowired JwtTokenUtil jwtUtil;
	@Autowired
	private UserDetailService userDetailService;
	
	@PostMapping("/login")
	public ResponseEntity<?> generateToken(@RequestBody AuthRequest authRequest) throws Exception{
		System.out.println("teste");
		try {
			authenticate(authRequest.getEmail(), authRequest.getPassword());
		}catch(UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("User does not exist or invalid credentials!");
		}
		// validated
		UserDetails userDetails = this.userDetailService.loadUserByUsername(authRequest.getEmail());
		String token = this.jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthResponse(token));
	}
	private void authenticate(String username, String password) throws Exception {
		try {
			
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (BadCredentialsException e) {
			throw new Exception("Invalid Credentials! "+e.getMessage());
		}catch(DisabledException e) {
			throw new Exception("User Disabled! "+e.getMessage());
		}
	}
	@PreAuthorize("hasAnyAuthority	('ROLE_ADMIN', 'ROLE_CUSTOMER')")
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/current-user")
	public User getCurrentUser(Principal principal) {
		return (User)this.userDetailService.loadUserByUsername(principal.getName());
	}

}
