package com.school.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
@Builder
public class Message implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String text;
	private String sentTo;
	private String receivedBy;

	@ManyToOne
//	@JsonIgnore
    private Teachers teachers;
	
	@Column(nullable=false, updatable = false)
	@CreationTimestamp
	private Date createdDate;
	
	@Column(nullable=false)
	@UpdateTimestamp
	private Date updatedDate;

	public Message(String text, String sentTo, String receivedBy) {
		super();
		this.text = text;
		this.sentTo = sentTo;
		this.receivedBy = receivedBy;
	}
	
	
}
