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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.school.dto.SchoolNoticeRequest;
import com.school.entity.SchoolNotice;
import com.school.entity.Student;
import com.school.repository.NoticeRepository;
import com.school.service.SchoolNoticeService;

@SpringBootTest
public class SchoolNoticeServiceTest {
	
	@MockBean
	private NoticeRepository noticeRepository;
	
	@Autowired 
	private SchoolNoticeService service;
	
	@Test
	public void test_addNotice() {
		
		SchoolNoticeRequest noticeRequest = new SchoolNoticeRequest();
		
		SchoolNotice notice = SchoolNotice.build(1, "Parents Meeting", new Date(), new Date());
		
		when(noticeRepository.save(Mockito.any(SchoolNotice.class))).thenReturn(notice);
		
		assertEquals(notice, service.addNotice(noticeRequest));
		assertNotNull(notice);
	}
	
	@Test
	public void test_getAllNotice() {
		SchoolNotice notice1 = SchoolNotice.build(1, "Parents Meeting", new Date(), new Date());
		SchoolNotice notice2 = SchoolNotice.build(2, "Result will be announced tomorrow.", new Date(), new Date());

		
		List<SchoolNotice> notice = new ArrayList<>();
		notice.add(notice1);
		notice.add(notice2);
		
		when(service.getAllNotice()).thenReturn(notice);
		
		assertThat(notice.size()).isGreaterThan(0);
	}
	
	// JUnit test for getAllNotice method
    /**
     * (negative scenario)
     */
    @DisplayName("JUnit test for getAllNotice method (negative scenario)")
    @Test
    public void givenEmptyNoticeList_whenGetNoticeList_thenReturnEmptyNoticeList(){
        // given - precondition or setup

    	SchoolNotice notice1 = SchoolNotice.build(1, "Parents Meeting", new Date(), new Date());
        given(service.getAllNotice()).willReturn(Collections.emptyList());

        // when -  action or the behaviour that we are going test
        List<SchoolNotice> noticeList = service.getAllNotice();

        // then - verify the output
        assertThat(noticeList).isEmpty();
        assertThat(noticeList.size()).isEqualTo(0);
    }
	
	@Test
	public void test_updateNotice() {
		SchoolNotice notice = SchoolNotice.build(1, "Parents Meeting", new Date(), new Date());
		
		when(noticeRepository.findById(Mockito.anyInt())).thenReturn(notice);
		
		SchoolNoticeRequest newNotice = new SchoolNoticeRequest();
		newNotice.setText("Result is postponed");
		
		service.updateNotice(notice.getNoticeId(), newNotice);
		assertThat(newNotice.getText()).isEqualTo("Result is postponed");
	}

	@Test
	public void test_deleteNotice() {
		
		doNothing().when(noticeRepository).deleteById(Mockito.anyInt());
	}
}
