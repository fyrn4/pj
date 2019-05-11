package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Food;
import com.example.demo.model.FoodReply;
import com.example.demo.model.News;
import com.example.demo.model.NewsReply;
import com.example.demo.model.Notice;
import com.example.demo.model.NoticeReply;
import com.example.demo.model.Photo;
import com.example.demo.model.PhotoReply;
import com.example.demo.model.Schedule;
import com.example.demo.model.ScheduleReply;
import com.example.demo.repository.FoodReplyRepository;
import com.example.demo.repository.FoodRepository;
import com.example.demo.repository.NewsReplyRepository;
import com.example.demo.repository.NewsRepository;
import com.example.demo.repository.NoticeReplyRepository;
import com.example.demo.repository.NoticeRepository;
import com.example.demo.repository.PhotoReplyRepository;
import com.example.demo.repository.PhotoRepository;
import com.example.demo.repository.ScheduleReplyRepository;
import com.example.demo.repository.ScheduleRepository;

@Service
public class ReplyService {
	
	@Autowired
	ScheduleReplyRepository scheduleRep;
	@Autowired
	FoodReplyRepository foodRep;
	@Autowired
	NewsReplyRepository newsRep;
	@Autowired
	PhotoReplyRepository photoRep;
	@Autowired
	NoticeReplyRepository noticeRep;
	
	@Autowired
	ScheduleRepository scherepo;
	@Autowired
	FoodRepository fdrepo;
	@Autowired
	NewsRepository nsrepo;
	@Autowired
	PhotoRepository ptrepo;
	@Autowired
	NoticeRepository ncrepo;

	
	public List<ScheduleReply> viewList(int bno) {
		return scheduleRep.findScheduleReplyByBnoOrderByRnoDesc(bno);
	}
	public List<FoodReply> foodReplyList(int bno){ 
		return foodRep.findFoodReplyByBnoOrderByRnoDesc(bno);
	}
	public List<NewsReply> newsReplyList(int bno){ 
		return newsRep.findNewsReplyByBnoOrderByRnoDesc(bno);
	}
	public List<PhotoReply> photoReplyList(int bno){ 
		return photoRep.findPhotoReplyByBnoOrderByRnoDesc(bno);
	}
	public List<NoticeReply> noticeReplyList(int bno){ 
		return noticeRep.findNoticeReplyByBnoOrderByRnoDesc(bno);
	}
	
	
	public void replyInsert(ScheduleReply schedule) {
		scheduleRep.save(schedule);
		int cnt = replyCount(schedule.getBno());
		Schedule obj = scherepo.findScheduleByNo(schedule.getBno());
		obj.setReplycnt(cnt);
		scherepo.save(obj);
	}
	public void replyFoodInsert(FoodReply food) {
		foodRep.save(food);
		int cnt = replyFoodCount(food.getBno());
		Food obj = fdrepo.findFoodByNo(food.getBno());
		obj.setReplycnt(cnt);
		fdrepo.save(obj);
	}
	public void replyNewsInsert(NewsReply news) {
		newsRep.save(news);
		int cnt = replyNewsCount(news.getBno());
		News obj = nsrepo.findNewsByNo(news.getBno());
		obj.setReplycnt(cnt);
		nsrepo.save(obj);
	}
	public void replyPhotoInsert(PhotoReply photo) {
		photoRep.save(photo);
		int cnt = replyPhotoCount(photo.getBno());
		Photo obj = ptrepo.findPhotoByNo(photo.getBno());
		obj.setReplycnt(cnt);
		ptrepo.save(obj);
	}
	public void replyNoticeInsert(NoticeReply notice) {
		noticeRep.save(notice);
		int cnt = replyNoticeCount(notice.getBno());
		Notice obj = ncrepo.findNoticeByNo(notice.getBno());
		obj.setReplycnt(cnt);
		ncrepo.save(obj);
	}
	
	
	
	public void replyDelete(int rno, int bno) {
		scheduleRep.deleteById(rno);
		int cnt = replyCount(bno);
		Schedule obj = scherepo.findScheduleByNo(bno);
		obj.setReplycnt(cnt);
		scherepo.save(obj);
	}
	public void replyFoodDelete(int rno, int bno) {
		foodRep.deleteById(rno);
		int cnt = replyFoodCount(bno);
		Food obj = fdrepo.findFoodByNo(bno);
		obj.setReplycnt(cnt);
		fdrepo.save(obj);
	}
	public void replyNewsDelete(int rno, int bno) {
		newsRep.deleteById(rno);
		int cnt = replyNewsCount(bno);
		News obj = nsrepo.findNewsByNo(bno);
		obj.setReplycnt(cnt);
		nsrepo.save(obj);
	}
	public void replyPhotoDelete(int rno, int bno) {
		photoRep.deleteById(rno);
		int cnt = replyPhotoCount(bno);
		Photo obj = ptrepo.findPhotoByNo(bno);
		obj.setReplycnt(cnt);
		ptrepo.save(obj);
	}
	public void replyNoticeDelete(int rno, int bno) {
		noticeRep.deleteById(rno);
		int cnt = replyNoticeCount(bno);
		Notice obj = ncrepo.findNoticeByNo(bno);
		obj.setReplycnt(cnt);
		ncrepo.save(obj);
	}
	
	
	
	public int replyCount(int bno){
		return scheduleRep.scheduleReplyCount(bno);
	}
	public int replyFoodCount(int bno){
		return foodRep.foodReplyCount(bno);
	}
	public int replyNewsCount(int bno){
		return newsRep.newsReplyCount(bno);
	}
	public int replyPhotoCount(int bno){
		return photoRep.photoReplyCount(bno);
	}
	public int replyNoticeCount(int bno){
		return noticeRep.noticeReplyCount(bno);
	}
}
