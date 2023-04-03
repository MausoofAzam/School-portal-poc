package com.school.service;

import java.util.List;

import com.school.dto.SchoolNoticeRequest;
import com.school.entity.SchoolNotice;

public interface SchoolNoticeService {

	public SchoolNotice addNotice(SchoolNoticeRequest noticeRequest);
	
	public List<SchoolNotice> getAllNotice();
	
	public SchoolNotice updateNotice(int id, SchoolNoticeRequest noticeRequest);
	
	public String deleteNotice(int id);
}
