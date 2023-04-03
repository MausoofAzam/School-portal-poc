package com.school.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
@Builder
public class MessageRequest {

	@NotEmpty(message="Please write some message in text.")
	private String text;
	
	@NotEmpty(message="sendTo shouldn't be empty or null")
	private String sentTo;
	
	@NotEmpty(message="receivedBy shouldn't be empty or null")
	private String receivedBy;
	
	private Integer teacherId;

	private Date createdDate;
	private Date updatedDate;
	public MessageRequest(@NotEmpty(message = "Please write some message in text.") String text,
			@NotEmpty(message = "sendTo shouldn't be empty or null") String sentTo,
			@NotEmpty(message = "receivedBy shouldn't be empty or null") String receivedBy) {
		super();
		this.text = text;
		this.sentTo = sentTo;
		this.receivedBy = receivedBy;
	}
	
	
}
