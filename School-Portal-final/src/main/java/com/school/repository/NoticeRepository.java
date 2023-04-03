package com.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.entity.SchoolNotice;

@Repository
public interface NoticeRepository extends JpaRepository<SchoolNotice, Integer>{

	SchoolNotice findById(int id);
}
