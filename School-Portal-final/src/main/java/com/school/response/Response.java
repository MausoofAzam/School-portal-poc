package com.school.response;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Response {

   public static ResponseEntity<Object> responseBuilder(String message, HttpStatus httpStatus, Object anyObject) {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("message", message);
        responseBody.put("httpStatus",httpStatus );
        responseBody.put("Data", anyObject);
        
        return new ResponseEntity<>(responseBody, httpStatus);
    }
}