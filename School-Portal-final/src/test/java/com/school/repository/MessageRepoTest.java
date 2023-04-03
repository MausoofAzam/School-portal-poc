package com.school.repository;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.school.entity.Message;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

class MessageRepoTest {
	@Autowired
	private MessageRepo messageRepo;
	
	
//	@BeforeEach
//	public void setup() {
//		Message message = Message.builder()
//				.text("Hii there..! ")
//				.sentTo("Madhan Kumar")
//				.receivedBy("Madhan Kumar")
//				.build();
//	}

//	JUnit testing for create of Message
	
//	@DisplayName("JUnit testing for create of Message")
	@Test
	public void test_create_message() {
		// given - precondition or setup
		Message message = Message.builder()
						.text("Hii there..! ")
						.sentTo("Madhan Kumar")
						.receivedBy("Madhan Kumar")
						.build();
		
		// when - action or behavior that we are going test
		Message newMessage = messageRepo.save(message);
		
		//then verifying the output
		assertThat(message).isNotNull();
		assertThat(message.getId()).isGreaterThan(0);
	}
	
	//JUnit test Case for find All Messages.
	
//	@DisplayName("JUnit test Case for find All Messages.")
//	@Test
//	public void test_find_All_Messages() {
//		//pre-conditions are
//		Message message = Message.builder()
//				.text("Hii there..! ")
//				.sentTo("Madhan Kumar")
//				.receivedBy("Madhan Kumar")
//				.build();
//		
//		Message message1 = Message.builder()
//				.text("Be Prepare for your exams ")
//				.sentTo("Suraj Kumar")
//				.receivedBy("Madhan Kumar")
//				.build();
//		
//		messageRepo.save(message);
//		messageRepo.save(message1);
//		
//		//Behavior that we are going to test
//		List<Message> messageList = messageRepo.findAll();
//		
//		//now verifying the output
//		
//		assertThat(messageList).isNotEmpty();
//		assertThat(messageList.size()).isGreaterThan(0);
//		
//	}
	/**	JUnit test case for update message
	 * 
	 */
	@DisplayName("Junit test case for update message")
	@Test
	public void test_update_message() {
					Message message = Message.builder()
									.text("Be Prepare for your exams ")
									.sentTo("Suraj Kumar")
									.receivedBy("Madhan Kumar")
									.build();
	
					 messageRepo.save(message);
	
	//the actions and behavior that we are going to test
					 Message savedStudent = messageRepo.findById(message.getId());
					 savedStudent.setText("Hello EveryOne..! How are you doing.");
					 savedStudent.setSentTo("Vijay Prakash");
					 
					 Message updatedStudent = messageRepo.save(savedStudent);

	//now verifying the output
					 
					 assertThat(savedStudent.getText()).isEqualTo("Hello EveryOne..! How are you doing.");
	}
	
	/**JUnit test cases for delete messages
	 * 
	 */	

	@DisplayName("JUnit test cases for delete messages")
	@Test
	public void test_delete_Messages() {
		//pre-conditions are
		Message message = Message.builder()
				.text("Be Prepare for your exams ")
				.sentTo("Suraj Kumar")
				.receivedBy("Madhan Kumar")
				.build();
		messageRepo.save(message);
		
		
		//the action and behavior that we are going to test.
		messageRepo.delete(message);
		Message findById = messageRepo.findById(message.getId());
		assertThat(findById).isNull();
	}

	/**	JUnit test cases for findMessageById
	 * 
	 */
	@DisplayName("JUnit test cases for findMessageById")
	@Test
	public void test_find_Message_By_Id() {
		//pre-conditions are 
		
		Message message = Message.builder()
				.text("Be Prepare for your exams ")
				.sentTo("Suraj Kumar")
				.receivedBy("Madhan Kumar")
				.build();
		
		messageRepo.save(message);
		
		//the actions or behavior that we are going to test
		
		Message messageDB = messageRepo.findById(message.getId());
		
		//now verifying the outputs
		
		assertThat(messageDB).isNotNull();
		
	}
	
}
