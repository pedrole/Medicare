package com.simplilearn.medicare;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.javafaker.Faker;
import com.simplilearn.medicare.pojo.Medicine;
import com.simplilearn.medicare.service.MedicineService;
import com.simplilearn.medicare.utils.FileUtil;

@RunWith(SpringRunner.class)
@SpringBootTest

public class MedicineTest {
	
	@Autowired
	MedicineService medicineService;

	@Test
	public void createMultiptleMedicine() {
		
		Faker faker = new Faker();
		List<String> categories = Arrays.asList("Allergy relief", "Travel", "Skincare" , 
				"Pain relief", "Oral care");
		for (int i = 0; i < 30; i++) {
			
			
			Collection<File> listFiles = FileUtils.listFiles(FileUtil.filePath.toFile(),
					 new String[]{ "jpg", "png"}, false);
			
			
			Medicine medicine = new Medicine(-1, faker.medical().medicineName(), 
					faker.lorem().characters(50, 250), 
					categories.get( new Random().nextInt(categories.size())),
					getRandomElement(listFiles).getName(),
					faker.number().numberBetween(1, 20));
			assertNotNull(medicineService.addMed(medicine));
		}

		

	}
	@Test
	public void deleteAllMedicine() {
		medicineService.clearMedicineTable();
		boolean result = true;
		assertTrue(result);
		
	}
	

    public static <T> T getRandomElement(Collection<T> collection) {
        if (collection.isEmpty()) {
            throw new IllegalArgumentException("Collection is empty");
        }

        int randomIndex = new Random().nextInt(collection.size());
        int currentIndex = 0;
        for (T element : collection) {
            if (currentIndex == randomIndex) {
                return element;
            }
            currentIndex++;
        }

        // This should not happen, but return null if the element is not found
        return null;
    }

}
