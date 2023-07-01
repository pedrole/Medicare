package com.simplilearn.medicare.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simplilearn.medicare.pojo.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
	
	 
	
}
