package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Schedule;
import com.example.demo.model.ScheduleReply;
import com.example.demo.repository.ScheduleReplyRepository;
import com.example.demo.repository.ScheduleRepository;

@RestController
public class ReplyController {
	
	@Autowired
	private ScheduleReplyRepository repo;
	
/*	@GetMapping("/reply/schedule/{bno}")
	public List<ScheduleReply> a(@PathVariable int bno,Model m) {
		List<ScheduleReply> list = repo.findScheduleReplyByBno(bno);
		return list;
	}*/
}
