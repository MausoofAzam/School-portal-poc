package com.school.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
@Entity
@Data
@Builder
public class Teachers implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String teachersName;
	private String subjects;
	@OneToMany(mappedBy = "teachers")
	@JsonIgnore
	List<Message> messages;
	
	public Teachers(int id, String teachersName, String subjects) {
		super();
		this.id = id;
		this.teachersName = teachersName;
		this.subjects = subjects;
	}
	
	
}
