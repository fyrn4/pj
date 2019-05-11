package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.FoodReply;
import com.example.demo.model.NewsReply;
import com.example.demo.model.NoticeReply;
import com.example.demo.model.PhotoReply;
import com.example.demo.model.ScheduleReply;
import com.example.demo.service.ReplyService;

@Controller
public class ReplyController {
	
	@Autowired
	ReplyService rs;
	
	@PostMapping("/schedule/reply")
	public String secheduleReplyInsert(ScheduleReply sr) {
		String str = sr.getReply();
		str = str.replaceAll("\n" , "<br/>");
		sr.setReply(str);
		rs.replyInsert(sr);
		int viewNo = sr.getBno();
		return "redirect:../pgm/schedule/view?no="+viewNo;
	}
	
	@PostMapping("/news/reply")
	public String newsReplyInsert(NewsReply sr) {
		String str = sr.getReply();
		str = str.replaceAll("\n" , "<br/>");
		sr.setReply(str);
		rs.replyNewsInsert(sr);
		int viewNo = sr.getBno();
		return "redirect:../comm/news/view?no="+viewNo;
	}
	
	@PostMapping("/notice/reply")
	public String noticeReplyInsert(NoticeReply sr) {
		String str = sr.getReply();
		str = str.replaceAll("\n" , "<br/>");
		sr.setReply(str);
		rs.replyNoticeInsert(sr);
		int viewNo = sr.getBno();
		return "redirect:../comm/notice/view?no="+viewNo;
	}
	
	@PostMapping("/photo/reply")
	public String photoReplyInsert(PhotoReply sr) {
		String str = sr.getReply();
		str = str.replaceAll("\n" , "<br/>");
		sr.setReply(str);
		rs.replyPhotoInsert(sr);
		int viewNo = sr.getBno();
		return "redirect:../comm/photo/view?no="+viewNo;
	}
	
	@PostMapping("/menu/reply")
	public String foodReplyInsert(FoodReply sr) {
		String str = sr.getReply();
		str = str.replaceAll("\n" , "<br/>");
		sr.setReply(str);
		rs.replyFoodInsert(sr);
		int viewNo = sr.getBno();
		return "redirect:../pgm/menu/view?no="+viewNo;
	}
	
	
	@GetMapping("/schedule/reply/delete")
	public String scheduleReplyDelete(@RequestParam int rno, @RequestParam int bno) {
		rs.replyDelete(rno,bno);
		return "redirect:../../pgm/schedule/view?no="+bno;
	}
	@GetMapping("/menu/reply/delete")
	public String foodReplyDelete(@RequestParam int rno, @RequestParam int bno) {
		rs.replyFoodDelete(rno, bno);
		return "redirect:../../pgm/menu/view?no="+bno;
	}
	@GetMapping("/photo/reply/delete")
	public String photoReplyDelete(@RequestParam int rno, @RequestParam int bno) {
		rs.replyPhotoDelete(rno,bno);
		return "redirect:../../comm/photo/view?no="+bno;
	}
	@GetMapping("/news/reply/delete")
	public String newsReplyDelete(@RequestParam int rno, @RequestParam int bno) {
		rs.replyNewsDelete(rno,bno);
		return "redirect:../../comm/news/view?no="+bno;
	}
	@GetMapping("/notice/reply/delete")
	public String noticeReplyDelete(@RequestParam int rno, @RequestParam int bno) {
		rs.replyNoticeDelete(rno,bno);
		return "redirect:../../comm/notice/view?no="+bno;
	}
}
