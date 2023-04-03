package com.school.controller;

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

import com.school.dto.MessageRequest;
import com.school.entity.Message;
import com.school.entity.Teachers;
import com.school.exception.IdNotFoundException;
import com.school.response.Response;
import com.school.service.MessageService;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/message")
public class MessageController {
	
	@Autowired
	MessageService service;
	
	@PostMapping("/add")
	public ResponseEntity<?> addMessage(@RequestBody @Valid MessageRequest messageRequest) {
		Message newMessage = service.createMessage(messageRequest);
		return Response.responseBuilder("Created successfully", HttpStatus.CREATED, newMessage);

	}
	
	@GetMapping("/")
	public ResponseEntity<?> getAllMessage(){
		List<Message> messageList = service.getAllMessage();
		if (messageList.size() > 0) {
			return Response.responseBuilder("Total message in database: [" + messageList.size() + "]..", HttpStatus.OK,
					messageList);
		}
		else
			throw new IdNotFoundException("No message is present");
	}
	
	@GetMapping("/{id}")
	public Message findById(@PathVariable Integer id) {
		return service.getMessageById(id);
	}
	
	@PutMapping("/updateMessage/{id}")
	public Message updateMessage(@PathVariable int id, @RequestBody @Valid MessageRequest messageRequest) {
		return service.updateMessage(id, messageRequest);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteMesssage(@PathVariable int id) {
		return service.deleteMessage(id);
	}
}
