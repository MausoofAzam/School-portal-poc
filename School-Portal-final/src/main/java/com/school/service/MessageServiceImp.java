package com.school.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.dto.MessageRequest;
import com.school.entity.Message;
import com.school.entity.Teachers;
import com.school.exception.IdNotFoundException;
import com.school.repository.MessageRepo;
import com.school.repository.TeachersRepository;

@Service
public class MessageServiceImp implements MessageService {

	@Autowired
	private MessageRepo messageRepo;
	
	@Autowired
	private TeachersRepository teachersRepository;

	@Override
	public Message createMessage(MessageRequest messageRequest) {
		
		Optional<Teachers> teachers = teachersRepository.findById(messageRequest.getTeacherId());
		Message msg = new Message();
		
		if(teachers.isPresent()) {
			msg.setText(messageRequest.getText());
			msg.setSentTo(messageRequest.getSentTo());
			msg.setReceivedBy(messageRequest.getReceivedBy());
			msg.setTeachers(teachers.get());
			msg.setCreatedDate(new Date());
			return messageRepo.save(msg);
		}else {
			return null;
			//return new Message();
		}
		
	}

	@Override
	public List<Message> getAllMessage() {
		return messageRepo.findAll();
	}

	@Override
	public Message updateMessage(int id, MessageRequest messageRequest) {
		Message msg = messageRepo.findById(id);

		if (msg != null) {
			msg.setText(messageRequest.getText());
			msg.setSentTo(messageRequest.getSentTo());
			msg.setReceivedBy(messageRequest.getReceivedBy());

			return messageRepo.save(msg);
		}
		throw new IdNotFoundException("The message with this id not found!!");
	}

	@Override
	public String deleteMessage(int id) {
		Message msg = messageRepo.findById(id);

		if (msg != null) {
			messageRepo.deleteById(id);
			return "Deleted successfully";
		}
		throw new IdNotFoundException("The message with this id not found!!");
	}

	@Override
	public Message getMessageById(int id) {

		return messageRepo.getById(id);
	}
}
