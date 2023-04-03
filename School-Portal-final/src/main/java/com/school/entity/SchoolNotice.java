package com.school.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
@Entity
public class SchoolNotice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int noticeId;
	private String text;
	
	@Column(nullable = false, updatable = false)
	@CreationTimestamp
	private Date createdDate;
	
	@Column(nullable = false)
	@UpdateTimestamp
	private Date updatedDate;
}
