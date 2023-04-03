package com.school.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class SchoolNoticeRequest {

	@NotEmpty(message="Class shouldn't be null or empty")
	private String text;
	
	private Date createdDate;
	private Date updatedDate;
}
