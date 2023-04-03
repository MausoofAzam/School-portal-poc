package com.school;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.school.dto.MessageRequest;
import com.school.service.MessageService;

@Component
public class MessageRunner implements ApplicationRunner {

	
	@Autowired
	private MessageService messageService;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
	
//		MessageRequest mr = new MessageRequest(); 
//		mr.setTeacherId(3);
//		mr.setText("Welcome");
//		mr.setReceivedBy("Asif");
//		mr.setCreatedDate(new Date());
//		mr.setUpdatedDate(new Date());
//		System.out.println("Runner :  "+mr);
//		messageService.createMessage(mr);
	}

}
