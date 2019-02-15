package com.example.demo.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.QSchedule;
import com.example.demo.model.Schedule;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;



public interface ScheduleRepository extends CrudRepository<Schedule, Integer>,QuerydslPredicateExecutor<Schedule>{
	Schedule findScheduleByName(String name);
	Page<Schedule> findByNoGreaterThan(int no, Pageable paging);
	
	default Predicate makePredicate(String type, String keyword) {
		BooleanBuilder builder = new BooleanBuilder();
		QSchedule sche = QSchedule.schedule;
		
		builder.and(sche.no.gt(0));
		
		if(type==null) {
		return builder;
		}
		switch(type) {
		case "tc": builder.and(sche.title.like("%"+keyword+"%").or(sche.content.like("%"+keyword+"%")));
		}
		return builder;
	}
}
