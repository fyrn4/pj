package com.example.demo.service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.model.Food;
import com.example.demo.model.FoodUploadFile;
import com.example.demo.model.News;
import com.example.demo.model.NewsUploadFile;
import com.example.demo.model.Notice;
import com.example.demo.model.NoticeUploadFile;
import com.example.demo.model.PageVO;
import com.example.demo.model.Photo;
import com.example.demo.model.PhotoUploadFile;
import com.example.demo.model.Schedule;
import com.example.demo.model.ScheduleUploadFile;
import com.example.demo.repository.FoodFileRepository;
import com.example.demo.repository.FoodRepository;
import com.example.demo.repository.NewsFileRepository;
import com.example.demo.repository.NewsRepository;
import com.example.demo.repository.NoticeFileRepository;
import com.example.demo.repository.NoticeRepository;
import com.example.demo.repository.PhotoFileRepository;
import com.example.demo.repository.PhotoRepository;
import com.example.demo.repository.ScheduleFileRepository;
import com.example.demo.repository.ScheduleRepository;
import com.querydsl.core.types.Predicate;

@Service
public class BoardService {

	@Autowired
	ScheduleRepository scherepo;
	@Autowired
	FoodRepository foodrepo;
	@Autowired
	NewsRepository newsrepo;
	@Autowired
	NoticeRepository noticerepo;
	@Autowired
	PhotoRepository photorepo;
	
	@Autowired
	ScheduleFileRepository scheFileRepo;
	@Autowired
	FoodFileRepository foodFileRepo;
	@Autowired
	NewsFileRepository newsFileRepo;
	@Autowired
	PhotoFileRepository photoFileRepo;
	@Autowired
	NoticeFileRepository noticeFileRepo;
	
	public Page<Schedule> testPaging(int to) {
		Pageable pag = new PageRequest(to,10,Sort.Direction.DESC,"no");
		Page<Schedule> result = scherepo.findByNoGreaterThan(0, pag);
		return result;
	}
	public Page<Schedule> pager(Pageable pag){
		return scherepo.findAll(scherepo.makePredicate(null, null),pag);
	}
	public Page<Schedule> searching(PageVO vo,Pageable pag){
		
		return scherepo.findAll(scherepo.makePredicate(vo.getType(), vo.getKeyword() ), pag);
	}
	public Optional<Schedule> scheduleView(int no) {
		return scherepo.findById(no);
	}
	
	/*schedule Service*/
	public List<Schedule> scheduleList() {
		List<Schedule> list = (List<Schedule>) scherepo.findAll();
		return list;
	}

	public void scheduleInsert(Schedule schedule) {
		System.out.println(schedule.toString());
		scherepo.save(schedule);
	}

	public Long tempSchedule() {
		String uuid = UUID.randomUUID().toString();
		Schedule schedule = new Schedule();
		schedule.setContent("temp result");
		schedule.setName(uuid);
		schedule.setTitle("temp result");
		Schedule result = scherepo.save(schedule);

		System.out.println(result.toString());
		Schedule ts = scherepo.findScheduleByName(result.getName());
		System.out.println(ts.getNo());

		return (long) ts.getNo();
	}

	public void scheduleUpdate(Schedule schedule, int bno) {

		Schedule update = scherepo.findById(bno).get();
		update.setTitle(schedule.getTitle());
		update.setName(schedule.getName());
		update.setContent(schedule.getContent());
		System.out.println("update:" + update.toString());
		scherepo.save(update);
	}

	public void checkDeleteImg(Schedule schedule, int bno) throws UnsupportedEncodingException {
		Schedule sd = scherepo.findById(bno).get();
		String str = sd.getContent();
		String[] splited = str.split("\\?fname=");
		ArrayList<String> result = new ArrayList<>();
		for (String item : splited) {
			if (item.contains("&amp;")) {
				result.add(URLDecoder.decode(item.split("&amp;")[0], "UTF-8"));
			}
		}
		System.out.println("ScheduleFiles:" + result);

		List<ScheduleUploadFile> uploadFiles = scheFileRepo.findAllFnameByBno(bno);
		System.out.println("uploadFiles:" + uploadFiles.toString());

		for (ScheduleUploadFile file : uploadFiles) {
			if (!result.contains(file.getFname())) {
				new File(file.getPath() + "/" + file.getFname()).delete();
				scheFileRepo.delete(file);
			}
		}
	}
	/*식단표 서비스*/
	public List<Food> foodList() {
		List<Food> list = (List<Food>) foodrepo.findAll();
		return list;
	}
	public List<Food> foodMainList(){
		List<Food> list = foodrepo.findFoodRecentlyList();
		return list;
	}

	public void foodInsert(Food food) {
		System.out.println(food.toString());
		foodrepo.save(food);
	}

	public Long tempFood() {
		String uuid = UUID.randomUUID().toString();
		Food food = new Food();
		food.setContent("temp result");
		food.setName(uuid);
		food.setTitle("temp result");
		Food result = foodrepo.save(food);

		System.out.println(result.toString());
		Food fs = foodrepo.findFoodByName(result.getName());
		System.out.println(fs.getNo());

		return (long) fs.getNo();
	}

	public void foodUpdate(Food food, int bno) {
		Food update = foodrepo.findById(bno).get();
		update.setTitle(food.getTitle());
		update.setName(food.getName());
		update.setContent(food.getContent());
		System.out.println("update:" + update.toString());
		foodrepo.save(update);
	}

	public void foodDeleteImg(Food food, int bno) throws UnsupportedEncodingException {
		Food fd = foodrepo.findById(bno).get();
		String str = fd.getContent();
		String[] splited = str.split("\\?fname=");
		ArrayList<String> result = new ArrayList<>();
		for (String item : splited) {
			if (item.contains("&amp;")) {
				result.add(URLDecoder.decode(item.split("&amp;")[0], "UTF-8"));
			}
		}
		System.out.println("FoodFiles:" + result);

		List<FoodUploadFile> uploadFiles = foodFileRepo.findAllFnameByBno(bno);
		System.out.println("uploadFiles:" + uploadFiles.toString());

		for (FoodUploadFile file : uploadFiles) {
			if (!result.contains(file.getFname())) {
				new File(file.getPath() + "/" + file.getFname()).delete();
				foodFileRepo.delete(file);
			}
		}
	}
	/*소식지 서비스*/
	public List<News> newsList() {
		List<News> list = (List<News>) newsrepo.findAll();
		return list;
	}
	
	public List<News> newsMainList() {
		List<News> list = newsrepo.findNewsRecentlyOne();
		return list;
	}
	public void newsInsert(News news) {
		System.out.println(news.toString());
		newsrepo.save(news);
	}

	public Long tempNews() {
		String uuid = UUID.randomUUID().toString();
		News news = new News();
		news.setContent("temp result");
		news.setName(uuid);
		news.setTitle("temp result");
		News result = newsrepo.save(news);

		System.out.println(result.toString());
		News fs = newsrepo.findNewsByName(result.getName());
		System.out.println(fs.getNo());

		return (long) fs.getNo();
	}

	public void newsUpdate(News news, int bno) {
		News update = newsrepo.findById(bno).get();
		update.setTitle(news.getTitle());
		update.setName(news.getName());
		update.setContent(news.getContent());
		System.out.println("update:" + update.toString());
		newsrepo.save(update);
	}

	public void newsDeleteImg(News news, int bno) throws UnsupportedEncodingException {
		News fd = newsrepo.findById(bno).get();
		String str = fd.getContent();
		String[] splited = str.split("\\?fname=");
		ArrayList<String> result = new ArrayList<>();
		for (String item : splited) {
			if (item.contains("&amp;")) {
				result.add(URLDecoder.decode(item.split("&amp;")[0], "UTF-8"));
			}
		}
		System.out.println("NewsFiles:" + result);

		List<NewsUploadFile> uploadFiles = newsFileRepo.findAllFnameByBno(bno);
		System.out.println("uploadFiles:" + uploadFiles.toString());

		for (NewsUploadFile file : uploadFiles) {
			if (!result.contains(file.getFname())) {
				new File(file.getPath() + "/" + file.getFname()).delete();
				newsFileRepo.delete(file);
			}
		}
	}
	/*공지사항 서비스 */
	public List<Notice> noticeList() {
		List<Notice> list = (List<Notice>) noticerepo.findAll();
		return list;
	}
	public List<Notice> noticeMainList() {
		List<Notice> list = noticerepo.findNoticeRecentlyList();
		return list;
	}
	public void foodInsert(Notice notice) {
		System.out.println(notice.toString());
		noticerepo.save(notice);
	}

	public Long tempNotice() {
		String uuid = UUID.randomUUID().toString();
		Notice notice = new Notice();
		notice.setContent("temp result");
		notice.setName(uuid);
		notice.setTitle("temp result");
		Notice result = noticerepo.save(notice);

		System.out.println(result.toString());
		Notice fs = noticerepo.findNoticeByName(result.getName());
		System.out.println(fs.getNo());

		return (long) fs.getNo();
	}

	public void noticeUpdate(Notice notice, int bno) {
		Notice update = noticerepo.findById(bno).get();
		update.setTitle(notice.getTitle());
		update.setName(notice.getName());
		update.setContent(notice.getContent());
		System.out.println("update:" + update.toString());
		noticerepo.save(update);
	}

	public void noticeDeleteImg(Notice notice, int bno) throws UnsupportedEncodingException {
		Notice fd = noticerepo.findById(bno).get();
		String str = fd.getContent();
		String[] splited = str.split("\\?fname=");
		ArrayList<String> result = new ArrayList<>();
		for (String item : splited) {
			if (item.contains("&amp;")) {
				result.add(URLDecoder.decode(item.split("&amp;")[0], "UTF-8"));
			}
		}
		System.out.println("NoticeFiles:" + result);

		List<NoticeUploadFile> uploadFiles = noticeFileRepo.findAllFnameByBno(bno);
		System.out.println("uploadFiles:" + uploadFiles.toString());

		for (NoticeUploadFile file : uploadFiles) {
			if (!result.contains(file.getFname())) {
				new File(file.getPath() + "/" + file.getFname()).delete();
				noticeFileRepo.delete(file);
			}
		}
	}
	/*포토갤러리 서비스 */
	public List<Photo> photoList() {
		List<Photo> list = (List<Photo>) photorepo.findAll();
		return list;
	}

	public void photoInsert(Photo photo) {
		System.out.println(photo.toString());
		photorepo.save(photo);
	}

	public Long tempPhoto() {
		String uuid = UUID.randomUUID().toString();
		Photo photo = new Photo();
		photo.setContent("temp result");
		photo.setName(uuid);
		photo.setTitle("temp result");
		Photo result = photorepo.save(photo);

		System.out.println(result.toString());
		Photo fs = photorepo.findPhotoByName(result.getName());
		System.out.println(fs.getNo());

		return (long) fs.getNo();
	}

	public void photoUpdate(Photo photo, int bno) {
		Photo update = photorepo.findById(bno).get();
		update.setTitle(photo.getTitle());
		update.setName(photo.getName());
		update.setContent(photo.getContent());
		System.out.println("update:" + update.toString());
		photorepo.save(update);
	}

	public void photoDeleteImg(Photo photo, int bno) throws UnsupportedEncodingException {
		Photo fd = photorepo.findById(bno).get();
		String str = fd.getContent();
		String[] splited = str.split("\\?fname=");
		ArrayList<String> result = new ArrayList<>();
		for (String item : splited) {
			if (item.contains("&amp;")) {
				result.add(URLDecoder.decode(item.split("&amp;")[0], "UTF-8"));
			}
		}
		System.out.println("photoFiles:" + result);

		List<PhotoUploadFile> uploadFiles = photoFileRepo.findAllFnameByBno(bno);
		System.out.println("uploadFiles:" + uploadFiles.toString());

		for (PhotoUploadFile file : uploadFiles) {
			if (!result.contains(file.getFname())) {
				new File(file.getPath() + "/" + file.getFname()).delete();
				photoFileRepo.delete(file);
			}
		}
	}
}
