package com.raghu.service.adduniversity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.raghu.model.entity.University;
import com.raghu.repository.university.UniversityRepository;

@Service
@Transactional(readOnly=true)
public class AddUniversityServiceImpl implements AddUniversityService{

	@Autowired
	private UniversityRepository universityRepository;
	
	@Transactional
	public void addUniversity(University universityDAO) {
		
		University university = new University();
		university.setUniversityName(universityDAO.getUniversityName());
		university.setUniversityCity(universityDAO.getUniversityCity());
		university.setUniversityCountry(universityDAO.getUniversityCountry());
		
		universityRepository.save(university);
	}
	
}
