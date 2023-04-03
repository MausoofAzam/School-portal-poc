package com.school.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.dto.TeachersRequest;
import com.school.entity.Teachers;
import com.school.exception.IdNotFoundException;
import com.school.repository.TeachersRepository;

@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private TeachersRepository repository;

	@Override
	public Teachers addTeacher(TeachersRequest teachersRequest) {
		Teachers teacher= new Teachers();
		teacher.setSubjects(teachersRequest.getSubjects());
		teacher.setTeachersName(teachersRequest.getTeachersName());
		return repository.save(teacher);
	}

	@Override
	public List<Teachers> getAllTeachers() {
		return repository.findAll();
	}
	
	@Override
	public Teachers getTeachers(int id) {
		return repository.findById(id).get();
	}

	@Override
	public Teachers updateTeacher(Teachers teachers) {
//		if (repository.existsById(teachers.getTeachers)) {
//			return repository.save(teachers);
//		} else {
//			return teachers;
//		}
		
		return null;
	}

	@Override
	public String deleteById(int id) {
			Teachers teachers =repository.findById(id).get();

			if(teachers != null) {
				repository.deleteById(id);
				return "Deleted successfully";
			}
			throw new IdNotFoundException("The message with this id not found!!");
	}
}


