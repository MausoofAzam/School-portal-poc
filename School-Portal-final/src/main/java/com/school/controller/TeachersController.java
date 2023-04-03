package com.school.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.school.dto.TeachersRequest;
import com.school.entity.Teachers;
import com.school.response.Response;
import com.school.service.TeacherService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/teachers")
@Slf4j
public class TeachersController {

	@Autowired
	private TeacherService service;

	@PostMapping("/")
	public ResponseEntity<?> createTask(@RequestBody @Valid TeachersRequest teachersRequest) {
		log.info("TeachersController :createTask executed :"+teachersRequest);
		
		return Response.responseBuilder("Task has created successfully.", HttpStatus.CREATED,
				service.addTeacher(teachersRequest));
	}

	@GetMapping("/")
	public ResponseEntity<?> findAllTeachers() {
		log.info("TeachersController:findAllTeachers() executed..!");

		List<Teachers> allTeachers = service.getAllTeachers();

		return Response.responseBuilder("total Teachers in Database [" + allTeachers.size() + "] ", HttpStatus.OK,
				allTeachers);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getTeacher(@PathVariable int id) {
		log.info("TeachersController:findAllTeachers() executed..!");

		Teachers teachers = service.getTeachers(id);
		return Response.responseBuilder("Teachers with id: " + id + " found!", HttpStatus.OK, teachers);

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateTeacher(@RequestBody Teachers newTeachers) {
		log.info("TeachersController:updateTeacher() task has executed..! ");
		return Response.responseBuilder("Updates Successfully", HttpStatus.OK, service.updateTeacher(newTeachers));

	}

	@DeleteMapping("/delete/{id}")
	public String deleteTeacher(@PathVariable int id) {
		log.info("TeachersController: deleteTeacher() executed..!");
		service.deleteById(id);
		return "Teachers with id: " + id + " deleted successfully";
	}

}
