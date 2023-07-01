package com.simplilearn.medicare.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplilearn.medicare.pojo.Medicine;
import com.simplilearn.medicare.repo.MedicineRepository;

@Service
public class MedicineService {
	
	@Autowired
	private MedicineRepository repo;
	 	
	public Medicine addMed(Medicine m) {
		return repo.save(m);
	}
	public List<Medicine> getAllMed(){
		return repo.findAll();
	}
	public Medicine getMedById(int id) {
		if(repo.findById(id).isPresent()) {
			return repo.findById(id).get();
		}
		else {
			return null;
		}
		
	}
	public void clearMedicineTable() {
        repo.deleteAll();
    }

   
    
	public Medicine updateMed(int id, Medicine newMed) {
		if(repo.findById(id).isPresent()) {
			Medicine oldMed= repo.findById(id).get();
			//oldMed.setName(newMed.getName());
			oldMed.setDescription(newMed.getDescription());
			oldMed.setCategory(newMed.getCategory());
			oldMed.setPrice(newMed.getPrice());
			//oldMed.setPhoto(newMed.getPhoto());			
			return repo.save(oldMed);
		}
		else {
			return null;
		}
	}
	
	
	public boolean deleteMedicine(int id) {
		if(repo.findById(id).isPresent()) {
			repo.deleteById(id);
			return true;
		}
		
		else {
			return false;
		}
	}
	
	

}
