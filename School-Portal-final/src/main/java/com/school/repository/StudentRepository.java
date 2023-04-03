package com.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.entity.Student;


@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{

	public Student findById(int id);			//Returns the entity identified by the given Long id.

}
