package com.school.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorType {
	
	private String message;
	private String code;
	private String classType;
	private String idReq;
}
