package com.school.service;

import java.util.List;

import com.school.dto.MessageRequest;
import com.school.entity.Message;

public interface MessageService {

	Message createMessage(MessageRequest messageRequest);
	
	List<Message> getAllMessage();
	
	Message getMessageById(int id);
	
	Message updateMessage(int id, MessageRequest messageRequest);
	
	String deleteMessage(int id);
}
