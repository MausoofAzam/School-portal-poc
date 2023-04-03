package com.school.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.dto.SchoolNoticeRequest;
import com.school.entity.SchoolNotice;
import com.school.exception.IdNotFoundException;
import com.school.repository.NoticeRepository;

@Service
public class SchoolNoticeServiceImp implements SchoolNoticeService{

	@Autowired
	NoticeRepository noticeRepository;
	
	@Override
	public SchoolNotice addNotice(SchoolNoticeRequest noticeRequest) {
		SchoolNotice notice = SchoolNotice.build(0, noticeRequest.getText(), noticeRequest.getCreatedDate(), noticeRequest.getUpdatedDate());
		return noticeRepository.save(notice);
	}

	@Override
	public List<SchoolNotice> getAllNotice() {
		List<SchoolNotice> notice = new ArrayList<>();
		noticeRepository.findAll().forEach(notice::add);
		return notice;
	}

	@Override
	public SchoolNotice updateNotice(int id, SchoolNoticeRequest noticeRequest) {
		SchoolNotice notice = noticeRepository.findById(id);
		
		if(notice != null) {
			notice.setText(noticeRequest.getText());
			return noticeRepository.save(notice);
		}
		else {
			throw new IdNotFoundException("This notice is not present");
		}
	}

	@Override
	public String deleteNotice(int id) {
		SchoolNotice notice = noticeRepository.findById(id);

		if(notice != null) {
			noticeRepository.deleteById(id);
			return "Deleted Successfully!!";
		}
		else {
			throw new IdNotFoundException("This notice is not present");
		}
	}

}
