package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.PhotoUploadFile;

public interface PhotoFileRepository extends CrudRepository<PhotoUploadFile, Integer>{
	PhotoUploadFile findPhotoUploadFileByFname(String fname);
	List<PhotoUploadFile> findAllFnameByBno(int bno);
	
}
