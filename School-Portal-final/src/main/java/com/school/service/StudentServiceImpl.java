package com.school.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.dto.StudentRequest;
import com.school.entity.Student;
import com.school.exception.IdNotFoundException;
import com.school.repository.StudentRepository;


@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	StudentRepository studentRepo;
	
	@Override
	public Student createStudent(StudentRequest studentRequest) {
		Student student = Student.build(0, studentRequest.getName(), studentRequest.getClassName(), studentRequest.getRollNo(), 
				studentRequest.getGrade(), studentRequest.getCreatedDate(), studentRequest.getUpdatedDate());
		return studentRepo.save(student);
	} 
	
	@Override
	public List<Student> getAllStudent() {
		List<Student> student = new ArrayList<>();
		studentRepo.findAll().forEach(student::add);
		return student;
	}

	@Override
	public Student getStudent(int id) {
		return studentRepo.findById(id);
	}

	@Override
	public Student updateStudent(int id, StudentRequest studentRequest) {
		Student existStudent = studentRepo.findById(id);
		
		if(existStudent != null) {
			existStudent.setName(studentRequest.getName());
			existStudent.setClassName(studentRequest.getClassName());
			existStudent.setRollNo(studentRequest.getRollNo());
			existStudent.setGrade(studentRequest.getGrade());
			
			return studentRepo.save(existStudent);
		}
		else {
			throw new IdNotFoundException("Student with this id not available, please change your request");
		}
	}

	@Override
	public String deleteOneStudent(int id) {
		
		Student existStudent = studentRepo.findById(id);
		if(existStudent != null) {
			studentRepo.deleteById(id);
			return "Deleted successfully!!";
		}
		else {
			throw new IdNotFoundException("Student with this id not available, please change your request");
		}
	}
}
