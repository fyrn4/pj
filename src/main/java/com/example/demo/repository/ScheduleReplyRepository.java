package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.ScheduleReply;

public interface ScheduleReplyRepository extends CrudRepository<ScheduleReply, Integer>{
	List<ScheduleReply> findScheduleReplyByBnoOrderByRnoDesc(int bno);
	@Query(value="select count(*) from tb_schedule_reply where bno=?1",nativeQuery=true)
	int scheduleReplyCount(int bno);
	
}
