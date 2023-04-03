package com.school.entity;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Student")
@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
@Builder
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "class", nullable = false)
	private String className;
	
	private int rollNo;
	
	private int grade; 
	
	@Column(nullable = false, updatable = false)
	@CreationTimestamp
	private Date createdDate;

	@Column(nullable = false)
	//@Column(nullable = false, insertable = false)
	@UpdateTimestamp
	private Date updatedDate;

//	@ManyToOne(fetch = FetchType.LAZY, optional = false)
//	private Teachers teacher;
}
