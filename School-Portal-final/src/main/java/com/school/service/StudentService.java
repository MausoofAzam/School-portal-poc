package com.school.service;


import java.util.List;

import com.school.dto.StudentRequest;
import com.school.entity.Student;


public interface StudentService {
	
	Student createStudent(StudentRequest studentRequest);

	List<Student> getAllStudent();
	
	Student getStudent(int id);
	
	Student updateStudent(int id, StudentRequest studentRequest);
	
	String deleteOneStudent(int id);

}
