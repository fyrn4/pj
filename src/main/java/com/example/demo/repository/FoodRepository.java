package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Food;

public interface FoodRepository extends CrudRepository<Food, Integer>{
	Food findFoodByName(String name);
	@Query(value="select * from tb_food ORDER BY no DESC LIMIT 5",nativeQuery=true)
	List<Food> findFoodRecentlyList();
}
