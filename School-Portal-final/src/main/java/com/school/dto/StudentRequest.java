package com.school.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import com.school.entity.Teachers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
@Builder
public class StudentRequest {

	@NotEmpty(message="Student name shouldn't be null or empty")
	private String name;
	
	@NotEmpty(message="Class shouldn't be null or empty")
	private String className;
		
	private int rollNo;
	private int grade; 
	
	//private Teachers teacher;

	private Date createdDate;
	private Date updatedDate;
}
