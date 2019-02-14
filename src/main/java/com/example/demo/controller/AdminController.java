package com.example.demo.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Food;
import com.example.demo.model.FoodUploadFile;
import com.example.demo.model.News;
import com.example.demo.model.NewsUploadFile;
import com.example.demo.model.Notice;
import com.example.demo.model.NoticeUploadFile;
import com.example.demo.model.Photo;
import com.example.demo.model.PhotoUploadFile;
import com.example.demo.model.Schedule;
import com.example.demo.model.ScheduleUploadFile;
import com.example.demo.service.BoardService;
import com.example.demo.service.ImageService;

@Controller
public class AdminController {
	
	@Autowired
	BoardService bs;
	@Autowired
	ImageService is;
	
	@GetMapping("/admin/integrate")
	public String adminPage() {
		return "admin/관리자";
	}
	/*일정표 서비스*/
	@GetMapping("/admin/schedule")
	public String scheduleForm(@AuthenticationPrincipal Principal principal, Model model) {
		if(principal != null) {
		model.addAttribute("name",principal.getName());
		}else{model.addAttribute("name", "관리자");}
		Long bno = bs.tempSchedule();
		System.out.println(bno);
		model.addAttribute("bno",bno);
		
		return "admin/관리자-일정표폼";
	}
	
	@PostMapping("/admin/schedule" )
	@ResponseBody
	public void scheduleFormPost(Schedule schedule, @RequestParam("bno") int bno) throws UnsupportedEncodingException {
		System.out.println("adminform:"+schedule.toString()+"&bno:"+bno);	
		bs.scheduleUpdate(schedule, bno);
		bs.checkDeleteImg(schedule, bno);
	}
	/*식단표 서비스*/
	@GetMapping("/admin/food")
	public String foodForm(@AuthenticationPrincipal Principal principal, Model model) {
		if(principal != null) {
		model.addAttribute("name",principal.getName());
		}else{model.addAttribute("name", "관리자");}
		Long bno = bs.tempFood();
		System.out.println(bno);
		model.addAttribute("bno",bno);
		
		return "admin/관리자-식단표폼";
	}
	
	@PostMapping("/admin/food" )
	@ResponseBody
	public void foodFormPost(Food food, @RequestParam("bno") int bno) throws UnsupportedEncodingException {
		System.out.println("adminform:"+food.toString()+"&bno:"+bno);	
		bs.foodUpdate(food, bno);
		bs.foodDeleteImg(food, bno);
	}
	/*소식지 서비스*/
	@GetMapping("/admin/news")
	public String newsForm(@AuthenticationPrincipal Principal principal, Model model) {
		if(principal != null) {
		model.addAttribute("name",principal.getName());
		}else{model.addAttribute("name", "관리자");}
		Long bno = bs.tempNews();
		System.out.println(bno);
		model.addAttribute("bno",bno);
		
		return "admin/관리자-소식지폼";
	}
	
	@PostMapping("/admin/news" )
	@ResponseBody
	public void newsFormPost(News news, @RequestParam("bno") int bno) throws UnsupportedEncodingException {
		System.out.println("adminform:"+news.toString()+"&bno:"+bno);	
		bs.newsUpdate(news, bno);
		bs.newsDeleteImg(news, bno);
	}
	/*공지사항 서비스*/
	@GetMapping("/admin/notice")
	public String noticeForm(@AuthenticationPrincipal Principal principal, Model model) {
		if(principal != null) {
		model.addAttribute("name",principal.getName());
		}else{model.addAttribute("name", "관리자");}
		Long bno = bs.tempNotice();
		System.out.println(bno);
		model.addAttribute("bno",bno);
		
		return "admin/관리자-공지사항폼";
	}
	
	@PostMapping("/admin/notice" )
	@ResponseBody
	public void noticeFormPost(Notice notice, @RequestParam("bno") int bno) throws UnsupportedEncodingException {
		System.out.println("adminform:"+notice.toString()+"&bno:"+bno);	
		bs.noticeUpdate(notice, bno);
		bs.noticeDeleteImg(notice, bno);
	}
	/*포토갤러리 서비스*/
	@GetMapping("/admin/photo")
	public String photoForm(@AuthenticationPrincipal Principal principal, Model model) {
		if(principal != null) {
		model.addAttribute("name",principal.getName());
		}else{model.addAttribute("name", "관리자");}
		Long bno = bs.tempPhoto();
		System.out.println(bno);
		model.addAttribute("bno",bno);
		
		return "admin/관리자-포토갤러리폼";
	}
	
	@PostMapping("/admin/photo" )
	@ResponseBody
	public void photoFormPost(Photo photo, @RequestParam("bno") int bno) throws UnsupportedEncodingException {
		System.out.println("adminform:"+photo.toString()+"&bno:"+bno);	
		bs.photoUpdate(photo, bno);
		bs.photoDeleteImg(photo, bno);
	}
	
	/*파일업로드 / 섬네일*/
	@PostMapping("/admin/image/{path}")
    @ResponseBody
    public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file, @PathVariable String path,
    		@RequestParam("bno")int bno) {
        try {
        	System.out.println(file.toString());
        	System.out.println("ofname="+file.getOriginalFilename()+" size:"+file.getSize());
            if(path.equals("schedule")) {
            	System.out.println("------------schedule 들어왓다-----------");
            	ScheduleUploadFile uploadedFile = is.store(file, path, bno);
            	String str = "/image?fname=" + URLEncoder.encode(uploadedFile.getFname(), "UTF-8")+"&ofname="
                		+URLEncoder.encode(uploadedFile.getOfname(), "UTF-8");
                return ResponseEntity.ok().body(str);
            }
            if(path.equals("food")) {
            	System.out.println("------------food 들어왓다-----------");
            	FoodUploadFile uploadedFile = is.foodstore(file, path, bno);
            	String str = "/image?fname=" + URLEncoder.encode(uploadedFile.getFname(), "UTF-8")+"&ofname="
                		+URLEncoder.encode(uploadedFile.getOfname(), "UTF-8");
                return ResponseEntity.ok().body(str);
            }
            if(path.equals("notice")) {
            	NoticeUploadFile uploadedFile = is.noticeStore(file, path, bno);
            	String str = "/image?fname=" + URLEncoder.encode(uploadedFile.getFname(), "UTF-8")+"&ofname="
                		+URLEncoder.encode(uploadedFile.getOfname(), "UTF-8");
                return ResponseEntity.ok().body(str);
            }
            if(path.equals("photo")) {
            	PhotoUploadFile uploadedFile = is.photoStore(file, path, bno);
            	String str = "/image?fname=" + URLEncoder.encode(uploadedFile.getFname(), "UTF-8")+"&ofname="
                		+URLEncoder.encode(uploadedFile.getOfname(), "UTF-8");
                return ResponseEntity.ok().body(str);
            }
            if(path.equals("news")) {
            	NewsUploadFile uploadedFile = is.newsStore(file, path, bno);
            	String str = "/image?fname=" + URLEncoder.encode(uploadedFile.getFname(), "UTF-8")+"&ofname="
                		+URLEncoder.encode(uploadedFile.getOfname(), "UTF-8");
                return ResponseEntity.ok().body(str);
            }
            
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
	
	@RequestMapping("/image")
	public void download(HttpServletRequest req, HttpServletResponse res,
			@RequestParam String fname, @RequestParam String ofname, @RequestParam String path) {
		System.out.println("fname:"+fname+", ofname:"+ofname);
		is.down(req, res, ofname, fname, path);
	}
	
	
	@PostMapping("/admin/ajax")
	@ResponseBody
	public ResponseEntity<?> test(){
		System.out.println("hiajax");
		
		return ResponseEntity.ok().body("/ajax");
	}
	
	@RequestMapping("/test")
	public String test1(Model model) throws IOException {
		return "test";
	}
}
