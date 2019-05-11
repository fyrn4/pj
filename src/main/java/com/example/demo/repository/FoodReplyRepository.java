package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.FoodReply;

public interface FoodReplyRepository extends CrudRepository<FoodReply, Integer>{
	List<FoodReply> findFoodReplyByBnoOrderByRnoDesc(int bno);
	@Query(value="select count(*) from tb_food_reply where bno=?1",nativeQuery=true)
	int foodReplyCount(int bno);
	
}
