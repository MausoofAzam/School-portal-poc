package com.school.repository;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.school.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace =AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;

	//JUnit Test cases for Create Users
	
	@DisplayName("JUnit Test cases for Create Users")
	@Test
	public void test_create_Users() {
		//preconditions are
		
		User user  = User.builder()
					.firstName("Madhan")
					.lastName("Kumar")
					.email("azam@gmail.com")
					.password("12345")
					.build();
		
		//Behavior that we are going to test
		
		User saveUser = userRepository.save(user);
		
		// now verify the output
		assertThat(saveUser).isNotNull();
		assertThat(saveUser.getId()).isGreaterThan(0);
	}
	

}
