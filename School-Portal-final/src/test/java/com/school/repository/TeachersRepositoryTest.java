package com.school.repository;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.school.entity.Teachers;

/**
 * @author Mausoof.azam
 *
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TeachersRepositoryTest {

	@Autowired
	private TeachersRepository teachersRepository;
		//JUnit testing for create Teacher
	
	@DisplayName("JUnit testing for create Teacher")
	@Test
	public void create_Teacher() {
		// given - precondition or setup
		Teachers teachers = Teachers.builder()
							.teachersName("Anurag Vaerma")
							.subjects("Math")
							.build();
		
		// when - action or behavior that we are going test
		Teachers newTeacher = teachersRepository.save(teachers);
		
		// then verify the output
		assertThat(newTeacher).isNotNull();
		assertThat(newTeacher.getId()).isGreaterThan(0);
	}
	
	//JUnit test cases for find all Teachers
	@DisplayName("JUnit test cases for find all Teachers")
	@Test
	public void findAll_Teacher() {
		// given - precondition or setup
		Teachers teachers = Teachers.builder()
				.teachersName("Anurag Vaerma")
				.subjects("Math")
				.build();
		
		Teachers teachers1 = Teachers.builder()
				.teachersName("Geetha")
				.subjects("Science")
				.build();
		
		teachersRepository.save(teachers);
		teachersRepository.save(teachers1);
		
		// when - action or behavior that we are going test
			List<Teachers> teachersList = teachersRepository.findAll();
			
		//then verify the response
			assertThat(teachersList).isNotEmpty();
			assertThat(teachersList.size()).isGreaterThan(0);
		
	}
	
	//JUnit test cases for Delete Teacher
	@DisplayName("JUnit test cases for Delete Teacher")
	@Test
	public void update_Teachers() {
		// given - precondition or setup
		Teachers teachers = Teachers.builder()
				.teachersName("Anurag Vaerma")
				.subjects("Math")
				.build();
		
		teachersRepository.save(teachers);
		// when - action or behavior that we are going test
		Teachers saveTeacher = teachersRepository.save(teachers);
		saveTeacher.setTeachersName("Aman");
		saveTeacher.setSubjects("History");
		
		Teachers updatedTeacher = teachersRepository.save(saveTeacher);
		//then verify the output
		
	}

	// JUnit test cases for getStudent by id
		@DisplayName("JUnit test cases for getStudent by id")
		@Test
		public void get_teachersBYId() {
			// given - precondition or setup
			Teachers teachers = Teachers.builder()
					.teachersName("Anurag Vaerma")
					.subjects("Math")
					.build();
			
			teachersRepository.save(teachers);
			// when - action or behavior that we are going test

			 Teachers  teachersDB = teachersRepository.findById(teachers.getId()).get();

			// then verify the output
			assertThat(teachersDB).isNotNull();
		}

		// JUnit test cases for update teachers
		@DisplayName("JUnit test cases for update teachers")
		@Test
		public void update_teachers() {
			// given - precondition or setup
			Teachers teachers = Teachers.builder()
					.teachersName("Anurag Vaerma")
					.subjects("Math")
					.build();
			

			teachersRepository.save(teachers);
			// when - action or behavior that we are going test

			Teachers savedTeacher = teachersRepository.findById(teachers.getId()).get();
			
			savedTeacher.setTeachersName("Ravi");
			savedTeacher.setSubjects("Science");
			
			Teachers updateTeacher = teachersRepository.findById(teachers.getId()).get();
			// then verify the output
			assertThat(savedTeacher.getTeachersName()).isEqualTo("Ravi");
			assertThat(savedTeacher.getSubjects()).isEqualTo("Science");
		}
		
		//JUnit test for delete Teacher
		@DisplayName("JUnit test for delete Teacher")
		@Test
		public void test_Delete_Teacher() {
			//given : prediction or setup
			Teachers teachers = Teachers.builder()
					.teachersName("Anurag Vaerma")
					.subjects("Math")
					.build();
			teachersRepository.save(teachers);
			
			//action  that we are going to test
//				teachersRepository.delete(teachers);
			teachersRepository.deleteById(teachers.getId());
				Optional<Teachers> teacherDB = teachersRepository.findById(teachers.getId());
			
				//now verifying the output
			assertThat(teacherDB).isEmpty();
			
		}

}

