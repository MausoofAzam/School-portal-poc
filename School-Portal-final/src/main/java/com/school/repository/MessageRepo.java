package com.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.entity.Message;


public interface MessageRepo extends JpaRepository<Message, Integer>{

	Message findById(int id);
}
