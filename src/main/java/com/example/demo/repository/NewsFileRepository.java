package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.NewsUploadFile;

public interface NewsFileRepository extends CrudRepository<NewsUploadFile, Integer>{
	NewsUploadFile findNewsUploadFileByFname(String fname);
	List<NewsUploadFile> findAllFnameByBno(int bno);
	
}
