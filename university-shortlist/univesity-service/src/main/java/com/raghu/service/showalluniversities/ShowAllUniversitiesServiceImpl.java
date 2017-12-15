package com.raghu.service.showalluniversities;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.raghu.model.entity.University;
import com.raghu.repository.university.UniversityRepository;

@Service
@Transactional(readOnly=true)
public class ShowAllUniversitiesServiceImpl implements ShowAllUniversitiesService{

	@Autowired
	private UniversityRepository universityRepository;
	
	public List<University> getAllUniversity() {

		return universityRepository.getAllUniversity();
		
	}

}
