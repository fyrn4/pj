package com.example.demo.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Photo;
import com.example.demo.model.QPhoto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public interface PhotoRepository extends CrudRepository<Photo, Integer>,QuerydslPredicateExecutor<Photo>{
	Photo findPhotoByName(String name);
	
	default Predicate makePredicate(String type, String keyword) {
		BooleanBuilder builder = new BooleanBuilder();
		QPhoto pt = QPhoto.photo;
		
		builder.and(pt.no.gt(0));
		
		if(type==null) {
		return builder;
		}
		switch(type) {
		case "tc": builder.and(pt.title.like("%"+keyword+"%").or(pt.content.like("%"+keyword+"%")));
		}
		return builder;
	}

	Photo findPhotoByNo(int no);
}
