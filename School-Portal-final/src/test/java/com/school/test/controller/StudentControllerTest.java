package com.school.test.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.dto.StudentRequest;
import com.school.entity.Student;
import com.school.service.StudentService;
import com.school.service.StudentServiceImpl;

	@WebMvcTest
	public class StudentControllerTest {

		@Autowired
		MockMvc mockMvc;

		@MockBean
		private StudentService service;

		@Autowired
		private ObjectMapper objectMapper;

		@Test
		public void test_saveStudent() throws Exception {

			// given - precondition or setup
			StudentRequest student = StudentRequest.builder().name("Resham").className("10th B").rollNo(1).grade(81).build();

			BDDMockito.given(service.createStudent(ArgumentMatchers.any(StudentRequest.class)))
					.willAnswer((invocation) -> invocation.getArgument(0));

			// when - action or the behavious that we are going test
			ResultActions response = mockMvc.perform(post("/students/create").contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(student)));

			// then - verify the output
			response.andDo(print()).andExpect(status().isCreated()).andExpect(jsonPath("$.name", is(student.getName())))
					.andExpect(jsonPath("$.className", is(student.getClassName())))
					.andExpect(jsonPath("$.rollNo", is(student.getRollNo())))
					.andExpect(jsonPath("$.grade", is(student.getGrade())));
		}

		// Junit test for Get all students REST API
		@Test
		public void givenListOfStudent_whenGetAllStudent_thenReturnStudentList() throws Exception {
			// given - precondition or setup
			List<Student> listOfStudents = new ArrayList<>();

			listOfStudents.add(Student.builder().id(1).name("Resham").className("10th B").rollNo(1).grade(90).build());
			listOfStudents.add(Student.builder().id(2).name("Azam").className("9th B").rollNo(2).grade(99).build());
			listOfStudents.add(Student.builder().id(3).name("Sandhya").className("8th B").rollNo(3).grade(100).build());

			BDDMockito.given(service.getAllStudent()).willReturn(listOfStudents);

			// when - action or the behavious that we are going test
			ResultActions response = mockMvc.perform(get("/students"));

			// then - verify the output
			response.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
					.andExpect(jsonPath("$.size()", is(listOfStudents.size())));
		}

		// Positive scenario valid student id
		// Junit test for GET student by id REST API
		@Test
		public void givenStudentId_whenGetStudentId_thenReturnStudentObject() throws Exception {

			// given - precondition or setup
			int id = 1;
			Optional<Student> student = Optional.of(Student.builder().name("Resham").className("10th B").rollNo(1).grade(81).build());

//			BDDMockito.given(service.getStudent(id)).willReturn(student);

			// when - action or the behaviour that was are going test
			ResultActions response = mockMvc.perform(get("/students/{id}", id));

			// then - verify the output
			response.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.name", is(student.get().getName())))
					.andExpect(jsonPath("$.className", is(student.get().getClassName())))
					.andExpect(jsonPath("$.rollNo", is(student.get().getRollNo())))
					.andExpect(jsonPath("$.grade", is(student.get().getGrade())));
		}

		// Positive scenario valid student id
		// Junit test for GET student by id REST API
		@Test
		public void givenStudentId_whenGetStudentId_thenReturnEmpty() throws Exception {

			// given - precondition or setup
			int id = 1;
			Student student = Student.builder().name("Resham").className("10th B").rollNo(1).grade(81).build();

			BDDMockito.given(service.getStudent(id)).willReturn(student);

			// when - action or the behaviour that was are going test
			ResultActions response = mockMvc.perform(get("/students/{id}", id));

			// then - verify the output
			response.andExpect(status().isNotFound()).andDo(print());
		}

		// JUnit test for update student REST API - Positive scenario
		@Test
		@Disabled
		public void givenUpdatedStudent_whenUpdateStudent_thenReturnUpdateStudentObject() throws Exception {

			// given - prrecondition or setup
			int id = 1;
			Student saveStudent = Student.builder().name("Resham").className("10th B").rollNo(1).grade(87).build();

			Student updateStudent = Student.builder().name("Rashmi").className("10th B").rollNo(1).grade(87).build();

//			when(service.getStudent(id)).thenReturn(Optional.of(saveStudent));
//		    when(service.updateStudent(any(Student.class))).thenReturn(updateStudent);

//		    mockMvc.perform(put("/students/update/{id}",id).contentType(MediaType.APPLICATION_JSON)
//		        .content(objectMapper.writeValueAsString(updateStudent)))
//		        .andExpect(status().isOk())
//		       // .andExpect(jsonPath("$.id", is(updateStudent.getId())))
//		        .andExpect(jsonPath("$.name", is(updateStudent.getName())))
//				.andExpect(jsonPath("$.className", is(updateStudent.getClassName())))
//				.andExpect(jsonPath("$.rollNo", is(updateStudent.getRollNo())))
//				.andExpect(jsonPath("$.grade", is(updateStudent.getGrade())))
//		        .andDo(print());

//			BDDMockito.given(service.getStudent(id)).willReturn(Optional.of(saveStudent));
//			BDDMockito.given(service.updateStudent(any(Student.class)))
//					.willAnswer((invocation) -> invocation.getArgument(0));

			// when - action or the behaviour that we are going test
			ResultActions response = mockMvc.perform(put("/student/update/{id}", id)
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(updateStudent)));
//			
//			//then - verify the output
			response.andExpect(status().isOk())
					.andDo(print())
					.andExpect(jsonPath("$.name", is(updateStudent.getName())))
					.andExpect(jsonPath("$.className", is(updateStudent.getClassName())))
					.andExpect(jsonPath("$.rollNo", is(updateStudent.getRollNo())))
					.andExpect(jsonPath("$.grade", is(updateStudent.getGrade())));
		}

		// JUnit test for update student REST API - Positive scenario
//		@Test
//		public void givenUpdatedStudent_whenUpdateStudent_thenReturn404() throws Exception {
//
//			// given - prrecondition or setup
//			int id = 1;
//			Student saveStudent = Student.builder().name("Resham").className("10th B").rollNo(1).grade(87).build();
//
//			Student updateStudent = Student.builder().name("Rashmi").className("10th B").rollNo(1).grade(87).build();
//
//			BDDMockito.given(service.getStudent(id)).andReturns(Optional.empty());
//			BDDMockito.given(service.getAllStudent(any(Student.class)))
//					.willAnswer((invocation) -> invocation.getArgument(0));
//
//			// when - action or the behaviour that we are going test
//			ResultActions response = mockMvc.perform(put("/student/update/{id}", id).contentType(MediaType.APPLICATION_JSON)
//					.content(objectMapper.writeValueAsString(updateStudent)));
//
//			// then - verify the output
//			response.andExpect(status().isNotFound()).andDo(print());
//		}

		@Test
		public void givenStudentId_whenDeleteStudent_thenReturn200() throws Exception {
			// given - prrecondition or setup
			Student saveStudent = Student.builder().id(1).name("Resham").className("10th B").rollNo(1).grade(87).build();
					
	        doReturn("deleted student successfully!!").when(service).deleteOneStudent(saveStudent.getId());

			//BDDMockito.willDoNothing().given(service).deleteOneStudent(saveStudent.getId());

			// when - action or the behaviour that we are going test
			ResultActions response = mockMvc.perform(delete("/students/delete/{id}", saveStudent.getId()));

			// then - verify the output
			response.andExpect(status().isOk())
				.andDo(print());
		}
		
		@Test
		public void givenStudentId_whenDeleteStudent_thenReturnException() throws Exception {
			// given - prrecondition or setup
			Student saveStudent = Student.builder().id(1).name("Resham").className("10th B").rollNo(1).grade(87).build();
					
	        doReturn("deleted student successfully!!").when(service).deleteOneStudent(saveStudent.getId());

			when(service.deleteOneStudent(anyInt())).thenReturn(null);
			service.deleteOneStudent(saveStudent.getId());
		}
	}

