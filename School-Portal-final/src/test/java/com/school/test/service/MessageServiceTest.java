package com.school.test.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.school.dto.MessageRequest;
import com.school.entity.Message;
import com.school.entity.Teachers;
import com.school.repository.MessageRepo;
import com.school.service.MessageServiceImp;

@ExtendWith(SpringExtension.class)
public class MessageServiceTest {

	@Mock
	private MessageRepo messageRepository;
	
	@InjectMocks
	private MessageServiceImp messageService;
	
//	@Test
//	public void test_addMessage() {
//		
//		MessageRequest messageRequest = new MessageRequest();
//		
//		Message msg = new Message();
//		msg.setId(1);
//		msg.setText("Holiday");
//		msg.setSentTo("Resham");
//		msg.setReceivedBy("Sandya");
//		msg.setCreatedDate(new Date());
//		msg.setUpdatedDate(new Date());
//		
//		when(messageRepository.save(Mockito.any(Message.class))).thenReturn(msg);
//		
//		assertEquals(msg, messageService.createMessage(messageRequest));
//		assertNotNull(msg);
//	}
	
	@Test
	public void test_getAllMessage() {
				
		Message msg1 = new Message();
		msg1.setId(1);
		msg1.setText("Holiday");
		msg1.setSentTo("Resham");
		msg1.setReceivedBy("Sandya");
		msg1.setCreatedDate(new Date());
		msg1.setUpdatedDate(new Date());
		
		Message msg2 = new Message();
		msg2.setId(1);
		msg2.setText("Holiday");
		msg2.setSentTo("Resham");
		msg2.setReceivedBy("Sandya");
		msg2.setCreatedDate(new Date());
		msg2.setUpdatedDate(new Date());
		
		List<Message> message = new ArrayList<>();
		message.add(msg1);
		message.add(msg2);

		Mockito.when(messageRepository.findAll()).thenReturn(message);
        assertThat(message.size()).isGreaterThan(0);	
	}
	// JUnit test for getAllMessage method
    /**
     * (negative scenario)
     */
    @DisplayName("JUnit test for getAllMessage method (negative scenario)")
    @Test
    public void givenEmptyMessageList_whenGetMessageList_thenReturnEmptyMessageList(){
        // given - precondition or setup

    	Message message = Message.builder()
                .id(2)
                .text("Hii there..!")
                .sentTo("Arvind")
                .receivedBy("Ravi")
                .build();

        given(messageService.getAllMessage()).willReturn(Collections.emptyList());

        // when -  action or the behaviour that we are going test
        List<Message> messageList = messageService.getAllMessage();

        // then - verify the output
        assertThat(messageList).isEmpty();
        assertThat(messageList.size()).isEqualTo(0);
    }
	
	@Test
	public void test_updateMessage() {
		MessageRequest messageRequest = new MessageRequest();
		
		Message msg = new Message();
		msg.setId(1);
		msg.setText("Holiday");
		msg.setSentTo("Resham");
		msg.setReceivedBy("Sandya");
		msg.setCreatedDate(new Date());
		msg.setUpdatedDate(new Date());
		
		when(messageRepository.findById(Mockito.anyInt())).thenReturn(msg);
		
		Message msg1 = new Message();
		msg1.setText("Annual day!");
		
		messageService.updateMessage(msg.getId(), messageRequest);
		
		assertThat(msg1.getText()).isEqualTo("Annual day!");
	}
	
	@Test
	public void test_deleteMessage() {
		doNothing().when(messageRepository).deleteById(Mockito.anyInt());
	}
}
