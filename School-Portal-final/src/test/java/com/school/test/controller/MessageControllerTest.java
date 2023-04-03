package com.school.test.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.controller.MessageController;
import com.school.dto.MessageRequest;
import com.school.entity.Message;
import com.school.service.MessageService;

@WebMvcTest(controllers = MessageController.class)
public class MessageControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	private MessageService messageService;

	@Autowired
	ObjectMapper objectMapper;

//	@Test
//	void test_getAllMessage() throws Exception{
//		List<Message> list = new ArrayList<>();
//		list.add(MessageRequest.builder().text("Hii there").sentTo("Ajay").receivedBy("Anam").build());
////		list.add(MessageRequest.builder().text("Hii there").sentTo("Ajay").receivedBy("Anam").build());
////		list.add(MessageRequest.builder().text("Hii there Ramesh").sentTo("Ramesh").receivedBy("Anam").build());
//		
//		given(messageService.getAllMessage()).willReturn(list);
//		
//		// calling rest-Api
//				ResultActions resultActions = mockMvc.perform(get("/message").contentType(MediaType.APPLICATION_JSON));
//
//				// then verify the output
//
//				resultActions.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.size()", is(list.size())));
//	}

//	
	@Test
	void test_addMessage() throws JsonProcessingException, Exception {
		int id = 101;

		Message m1 = new Message("Hii there", "Ajay", "Raman");

//		when(messageService.createMessage(Mockito.any(Message.class))).thenAnswer(m1);
		when(messageService.createMessage(Mockito.any(MessageRequest.class))).thenReturn(m1);
		// calling rest APi
		ResultActions response = mockMvc.perform(post("/message/add").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(m1)));
		// then verify the response

		response.andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.text", is(m1.getText())))
				.andExpect(jsonPath("$.sentTo", is(m1.getSentTo())))
				.andExpect(jsonPath("$.receivedBy", is(m1.getReceivedBy())));

	}

}
