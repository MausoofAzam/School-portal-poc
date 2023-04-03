package com.school.test.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.school.dto.TeachersRequest;
import com.school.entity.Message;
import com.school.entity.Teachers;
import com.school.repository.TeachersRepository;
import com.school.service.TeacherServiceImpl;

@ExtendWith(SpringExtension.class)
public class TeacherServiceImpTest {

	@Mock
	private TeachersRepository teachersRepository;

	@InjectMocks
	private TeacherServiceImpl service;

	@Test
	public void test_addTeachers() {

		TeachersRequest teachersRequest = new TeachersRequest();

//		//Teachers teachers = new Teachers(1,"Seema","Maths");
//		//when(teachersRepository.save(Mockito.any(Teachers.class))).thenReturn(teachers);
//        //assertNotNull(teachers);

		Teachers teachers = new Teachers();
		teachers.setId(1);;
		teachers.setTeachersName("Suman");
		teachers.setSubjects("Science");
		List<Message> messageList = new ArrayList<>();

		Message msg1 = new Message();
		msg1.setId(1);
		msg1.setText("Tomorrow is holiday");
		msg1.setSentTo("Resham");
		msg1.setReceivedBy("Azam");
		msg1.setCreatedDate(new Date());
		msg1.setUpdatedDate(new Date());

		Message msg2 = new Message();
		msg2.setId(1);
		msg2.setText("Tomorrow's holiday is canceled");
		msg2.setSentTo("Azam");
		msg2.setReceivedBy("Resham");
		msg2.setCreatedDate(new Date());
		msg2.setUpdatedDate(new Date());

		messageList.add(msg1);
		messageList.add(msg2);
   
		msg1.setTeachers(teachers);
		msg2.setTeachers(teachers);

		teachers.setMessages(messageList);
		teachers = teachersRepository.save(teachers);

		Teachers teachers1 = Teachers.build(1, "Seema", "Maths", messageList);

		when(teachersRepository.save(Mockito.any(Teachers.class))).thenReturn(teachers1);

       // Teachers savedTeachers = service.addTeacher(teachers);

		assertEquals(teachers1, service.addTeacher(teachersRequest));
		assertNotNull(teachers1);
	}
    @Test
    public void test_getAllTeachers() {
    	Teachers teachers1 = new Teachers(1,"Seema","Maths");
    	Teachers teachers2 = new Teachers(2,"Jigna","History");

    	List<Teachers> teachers = new ArrayList<>();
    	teachers.add(teachers1);
    	teachers.add(teachers2);
    	
    	Mockito.when(service.getAllTeachers()).thenReturn(teachers);
        assertThat(teachers.size()).isGreaterThan(0);
    }
 // JUnit test for getAllTeachers method
    /**
     * (negative scenario)
     */
    @DisplayName("JUnit test for getAllEmployees method (negative scenario)")
    @Test
    public void givenEmptyTeacherList_whenGetTeacherList_thenReturnEmptyTeacherList(){
        // given - precondition or setup

    	Teachers teachers = Teachers.builder()
                .id(2)
                .teachersName("Arvind")
                .subjects("Math")
                .build();

        given(service.getAllTeachers()).willReturn(Collections.emptyList());

        // when -  action or the behaviour that we are going test
        List<Teachers> teacherList = service.getAllTeachers();

        // then - verify the output
        assertThat(teacherList).isEmpty();
        assertThat(teacherList.size()).isEqualTo(0);
    }
    
    @Test
    public void test_getTeachers() {
    	Optional<Teachers> teachers1 = Optional.ofNullable(new Teachers(1,"Seema","Maths"));

    	Mockito.when(teachersRepository.findById(Mockito.anyInt())).thenReturn(teachers1);
    	//assertNotNull(teachers1);
		
    	Optional<Teachers> expected = Optional.ofNullable(new Teachers());
		expected = Optional.ofNullable(service.getTeachers(teachers1.get().getId()));
		
		assertEquals(teachers1, expected);		
    }
    @Test
    public void test_updateTeachers() {
    	Optional<Teachers> teachers1 = Optional.ofNullable(new Teachers(1,"Seema","Maths"));

		Mockito.when(teachersRepository.findById(Mockito.anyInt())).thenReturn(teachers1);
	    //set new value
	    teachers1.get().setTeachersName("Gayatri");
	    when(teachersRepository.save(Mockito.any(Teachers.class))).thenReturn(teachers1.get());
	    
	    assertNotNull(teachers1);
    }
    
    @Test
    public void deleteTeachers() {
        doNothing().when(teachersRepository).deleteById(Mockito.anyInt());
    }
}
