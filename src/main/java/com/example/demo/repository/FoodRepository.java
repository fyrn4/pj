package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Food;
import com.example.demo.model.QFood;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public interface FoodRepository extends CrudRepository<Food, Integer>, QuerydslPredicateExecutor<Food>{
	Food findFoodByName(String name);
	@Query(value="select * from tb_food ORDER BY no DESC LIMIT 5",nativeQuery=true)
	List<Food> findFoodRecentlyList();
	
	default Predicate makePredicate(String type, String keyword) {
		BooleanBuilder builder = new BooleanBuilder();
		QFood food = QFood.food;
		
		builder.and(food.no.gt(0));
		
		if(type==null) {
		return builder;
		}
		switch(type) {
		case "tc": builder.and(food.title.like("%"+keyword+"%").or(food.content.like("%"+keyword+"%")));
		}
		return builder;
	}
	Food findFoodByNo(int no);
}
