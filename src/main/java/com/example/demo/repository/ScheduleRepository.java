package com.example.demo.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.xmlunit.util.Predicate;

import com.example.demo.model.Schedule;

public interface ScheduleRepository extends CrudRepository<Schedule, Integer>{
	Schedule findScheduleByName(String name);
	Page<Schedule> findByNoGreaterThan(int no, Pageable paging);
	
	default Predicate makePredicate(String type, String keyword) {
		BooleanBuilder builder = new BooleanBuilder()
		return ;
	}
}
