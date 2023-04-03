package com.school.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.dto.StudentRequest;
import com.school.entity.Student;
import com.school.exception.StudentNotFoundException;
import com.school.response.Response;
import com.school.service.StudentService;

import javax.validation.Valid;

@RestController
@RequestMapping("/students")
public class StudentController {

	@Autowired
	StudentService studentService;

	@PostMapping("/create")
	public ResponseEntity<?> addStudent(@RequestBody @Valid StudentRequest studentRequest) {
		Student createStudent = studentService.createStudent(studentRequest);
//		return ResponseEntity.ok("New student added successfully!!");
		return Response.responseBuilder("New student added successfully", HttpStatus.CREATED, createStudent);
	}

	@GetMapping
	public ResponseEntity<Object> getAllStudent() {
		List<Student> studentList = studentService.getAllStudent();
		if (studentList.size() > 0) {

			return Response.responseBuilder("Total student in database: [" + studentList.size() + "]..", HttpStatus.OK,
					studentList);
		} else
			throw new StudentNotFoundException("No data of Student is present");
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getStudent(@PathVariable int id) {

		Student student = studentService.getStudent(id);
		if (student != null)
			return Response.responseBuilder("Student with id: " + id + " found!", HttpStatus.OK, student);
		else
			throw new StudentNotFoundException("No data of Student is present");
	}

	@PutMapping("/update/{id}")
	public Student updateStudent(@PathVariable int id, @RequestBody @Valid StudentRequest studentRequest) {
		return studentService.updateStudent(id, studentRequest);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteOneStudent(@PathVariable int id) {
		return studentService.deleteOneStudent(id);
	}
}
