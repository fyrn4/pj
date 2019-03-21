package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Schedule;
import com.example.demo.model.ScheduleUploadFile;

public interface ScheduleFileRepository extends CrudRepository<ScheduleUploadFile, Integer>{
	ScheduleUploadFile findScheduleUploadFileByFname(String fname);
	List<ScheduleUploadFile> findAllFnameByBno(int bno);
	
	 @Modifying
	 @Transactional
	 void deleteByBno(int bno);
}
