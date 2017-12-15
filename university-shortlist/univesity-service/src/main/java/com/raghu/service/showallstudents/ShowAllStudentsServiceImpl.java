package com.raghu.service.showallstudents;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.raghu.model.entity.Student;
import com.raghu.repository.student.StudentRepository;

@Service
@Transactional(readOnly=true)
public class ShowAllStudentsServiceImpl implements ShowAllStudentsService{

	@Autowired
	private StudentRepository studentRepository;
	
	public List<Student> getAllStudents() {
		return studentRepository.getAllStudents();
	}

}
