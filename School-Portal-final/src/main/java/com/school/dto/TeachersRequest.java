package com.school.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.school.entity.Message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
@Data
@Builder
public class TeachersRequest {

	@NotBlank(message="username shouldnot be null..!")
	private String teachersName;
	
	@NotBlank(message="subjects shouldnot be null..!")
	private String subjects;
	
   // private List<Student> student;
    
    private List<Message> message;

public TeachersRequest(@NotBlank(message = "username shouldnot be null..!") String teachersName,
		@NotBlank(message = "subjects shouldnot be null..!") String subjects) {
	super();
	this.teachersName = teachersName;
	this.subjects = subjects;
}
    
}
