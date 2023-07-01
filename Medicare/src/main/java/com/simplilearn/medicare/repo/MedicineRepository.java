package com.simplilearn.medicare.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simplilearn.medicare.pojo.Medicine;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine,Integer> {
	
	 
	
}
