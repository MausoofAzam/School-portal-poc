package com.school.repository;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.school.entity.Student;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class StudentRepositoryTest {

	@Autowired
	private StudentRepository studentRepository;

	// JUnit test case for save student
	@DisplayName("JUnit test case for save student")
	@Test
	public void save_Student() {
		// given - precondition or setup
		Student student = Student.builder().name("Savrav Tiwari").className("seventh-A").rollNo(20).grade(6).build();
		// when - action or behavior that we are going test
		Student savedStudent = studentRepository.save(student);

		// then verify the output
		assertThat(savedStudent).isNotNull();
		assertThat(savedStudent.getId()).isGreaterThan(0);

	}

	// JUnit Test cases for find All student
	@DisplayName("JUnit Test cases for find All student")
	@Test
	public void find_AllStudent() {
		// given - precondition or setup

		Student student = Student.builder().name("Azam").className("Tenth-A").rollNo(12).grade(7).build();

		Student student2 = Student.builder().name("Madhan").className("Seventh-A").rollNo(13).grade(8).build();

		studentRepository.save(student);
		studentRepository.save(student2);

		// when - action or behavior that we are going test
		List<Student> studentList = studentRepository.findAll();

		// then verify the output
		assertThat(studentList).isNotEmpty();
		assertThat(studentList.size()).isGreaterThan(0);

	}

	// JUnit test cases for getStudent by id
	@DisplayName("JUnit test cases for getStudent by id")
	@Test
	public void get_StudentBYId() {
		// given - precondition or setup

		Student student = Student.builder().name("Azam").className("Tenth-A").rollNo(27).grade(8).build();
		studentRepository.save(student);

		// when - action or behavior that we are going test

		Student studentDB = studentRepository.findById(student.getId());

		// then verify the output
		assertThat(studentDB).isNotNull();
	}

	// JUnit test cases for update student
	@DisplayName("JUnit test cases for update student")
	@Test
	public void update_Student() {
		// given - precondition or setup

		Student student = Student.builder().name("Suraj").className("Seventh-A").rollNo(18).grade(7).build();

		studentRepository.save(student);
		// when - action or behavior that we are going test

		Student savedstudent = studentRepository.findById(student.getId());
		savedstudent.setName("Ravi");
		savedstudent.setGrade(8);
		savedstudent.setClassName("Fifth-A");

		Student updatedStudent = studentRepository.save(savedstudent);

		// then verify the output
		assertThat(updatedStudent.getName()).isEqualTo("Ravi");
		assertThat(updatedStudent.getClassName()).isEqualTo("Fifth-A");
		assertThat(updatedStudent.getGrade()).isEqualTo(8);

	}
	//JUnit test cases for delete student
	
	@DisplayName("JUnit test cases for delete student")
	@Test
	public void test_delete_student() {
		// given prediction or setup
		Student student = Student.builder().name("Suraj").className("Seventh-A").rollNo(18).grade(7).build();
		studentRepository.save(student);
		
		//actions that we are going to test
		studentRepository.delete(student);
		Student studentDB = studentRepository.findById(student.getId());
		
		// then verify the output
		assertThat(studentDB).isNull();
		
	}
	

}
