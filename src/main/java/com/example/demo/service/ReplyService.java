package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Schedule;
import com.example.demo.model.ScheduleReply;
import com.example.demo.repository.ScheduleReplyRepository;
import com.example.demo.repository.ScheduleRepository;

@Service
public class ReplyService {
	
	@Autowired
	ScheduleReplyRepository reply;
	@Autowired
	ScheduleRepository sche;
	public List<ScheduleReply> viewList(int bno) {
		return reply.findScheduleReplyByBnoOrderByRnoDesc(bno);
	}
	
	public void replyInsert(ScheduleReply schedule) {
		reply.save(schedule);
		int cnt = replyCount(schedule.getBno());
		Schedule obj = sche.findScheduleByNo(schedule.getBno());
		obj.setReplycnt(cnt);
		sche.save(obj);
	}
	public void replyDelete(int rno, int bno) {
		reply.deleteById(rno);
		int cnt = replyCount(bno);
		Schedule obj = sche.findScheduleByNo(bno);
		obj.setReplycnt(cnt);
		sche.save(obj);
	}
	
	public int replyCount(int bno){
		return reply.scheduleReplyCount(bno);
	}
}
