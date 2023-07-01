package com.simplilearn.medicare.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simplilearn.medicare.pojo.Order;
import com.simplilearn.medicare.pojo.User;
import com.simplilearn.medicare.service.OrderService;

@CrossOrigin("http://localhost:4200/")
@RestController
@RequestMapping("/api/order")
public class OrderController {
	@Autowired
	private OrderService service;
	
	
	@PostMapping("/")
	@PreAuthorize("hasAuthority('ROLE_CUSTOMER')") 
	public ResponseEntity<Order> addOrder(@RequestBody Order order){
		
		Order order2 = service.addOrder(order);
		
		
		if (order2 != null)
			return new ResponseEntity<Order>(order2, HttpStatus.CREATED);
		else
			return new ResponseEntity<Order>(order2, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

}
