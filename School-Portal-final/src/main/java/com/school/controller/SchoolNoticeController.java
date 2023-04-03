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

import com.school.dto.SchoolNoticeRequest;
import com.school.entity.SchoolNotice;
import com.school.exception.IdNotFoundException;
import com.school.response.Response;
import com.school.service.SchoolNoticeServiceImp;

@RestController
@RequestMapping("/notice")
public class SchoolNoticeController {
	
	@Autowired
	SchoolNoticeServiceImp noticeService;

	@PostMapping("/add")
	public SchoolNotice addNotice(@RequestBody @Valid SchoolNoticeRequest noticeRequest) {
		return noticeService.addNotice(noticeRequest);
	}
	
	@GetMapping
	public ResponseEntity<?> getNotice(){
		List<SchoolNotice> noticeList = noticeService.getAllNotice();
		
		if(noticeList.size() > 0) {
			return Response.responseBuilder("Total notice in database: [" + noticeList.size() + "]..", HttpStatus.OK, noticeList);
		} else 
			throw new IdNotFoundException("Currently there is no notice");
	}
	
	@PutMapping("/update/{id}")
	public SchoolNotice updateNotice(@PathVariable int id, @RequestBody @Valid SchoolNoticeRequest noticeRequest) {
		return noticeService.updateNotice(id, noticeRequest);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteNotice(@PathVariable int id) {
		return noticeService.deleteNotice(id);
	}
}
