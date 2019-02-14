package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.FoodUploadFile;

public interface FoodFileRepository extends CrudRepository<FoodUploadFile, Integer>{
	FoodUploadFile findFoodUploadFileByFname(String fname);
	List<FoodUploadFile> findAllFnameByBno(int bno);
	
}
