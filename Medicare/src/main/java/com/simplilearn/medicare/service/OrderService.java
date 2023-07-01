package com.simplilearn.medicare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplilearn.medicare.pojo.Order;
import com.simplilearn.medicare.repo.OrderRepository;

@Service
public class OrderService {
	@Autowired
	private OrderRepository repo;
	
	public Order addOrder(Order order) {
		return repo.save(order);
	}

}
