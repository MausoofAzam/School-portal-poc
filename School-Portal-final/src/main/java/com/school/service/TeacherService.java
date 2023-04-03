package com.school.service;

import java.util.List;

import com.school.dto.TeachersRequest;
import com.school.entity.Teachers;

public interface TeacherService {

	Teachers addTeacher(TeachersRequest teachersRequest);
	
	List<Teachers> getAllTeachers();
	
	Teachers getTeachers(int id);

	Teachers updateTeacher(Teachers teachers);

	String deleteById(int id);


}
