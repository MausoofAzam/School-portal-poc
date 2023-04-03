//package com.school.test.controller;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.hamcrest.CoreMatchers.is;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.school.controller.TeachersController;
//import com.school.entity.Teachers;
//import com.school.service.TeacherService;
//
//@WebMvcTest(controllers = TeachersController.class)
//class TeachersControllerTest {
//
//	@Autowired
//	MockMvc mockMvc;
//	
//	@InjectMocks
//	private  TeacherService  teacherService;
//	
//	@Autowired
//	private ObjectMapper objectMapper;
//	
//	@Test
//	void test_findAllTeachers() {
//		List<Teachers> teacherList = new ArrayList<>();
//		teacherList.add(new Teachers(101, "Hii there", "Ajay kumar"));
//		teacherList.add(new Teachers(102, "Hii there", "Ajay kumar"));
//		when(teacherService.getAllTeachers().thenReturn(teacherList));
//		assertNotNull(teacherList);
//
//		// calling rest api
//		ResultActions response = mockMvc.perform(get("/teachers/").contentType(MediaType.APPLICATION_JSON));
//
//		// then - verify the output
//		response.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.size()", is(teacherList.size())));
//	}
//	
//
//    
//}
