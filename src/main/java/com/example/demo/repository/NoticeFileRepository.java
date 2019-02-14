package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.FoodUploadFile;
import com.example.demo.model.NoticeUploadFile;

public interface NoticeFileRepository extends CrudRepository<NoticeUploadFile, Integer>{
	NoticeUploadFile findNoticeUploadFileByFname(String fname);
	List<NoticeUploadFile> findAllFnameByBno(int bno);
	
}
