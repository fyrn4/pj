package com.example.demo.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Member;
import com.example.demo.model.PageMaker;
import com.example.demo.model.PageVO;
import com.example.demo.model.Schedule;
import com.example.demo.repository.ScheduleRepository;
import com.example.demo.service.BoardService;
import com.example.demo.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MainController {
	
	@Autowired
	MemberService ms;
	@Autowired
	BoardService bs;
	@Autowired
	ScheduleRepository scherepo;
	
	@GetMapping("/")
	public String mainPage(@AuthenticationPrincipal Principal principal, Model model) {
		model.addAttribute("news",bs.newsMainList());
		model.addAttribute("food",bs.foodMainList());
		model.addAttribute("notice",bs.noticeMainList());
		log.info("principal: {}", principal);		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
		System.out.println(authentication.getPrincipal());
		if(principal != null) {
			String userName = principal.getName();
		Member member = ms.memberlist(userName);
		model.addAttribute("test",member.getName());
		}
		
		return "main";
	}
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	@GetMapping("/signup")
	public String signUpPage() {
		return "signup";
	}
	@PostMapping("/signup")
	public String signUpPost(Member member, @RequestParam String pwd1, @RequestParam String pwd2) {
		if(pwd1.equals(pwd2)) {
			ms.signUp(member, pwd1, pwd2);
			System.out.println(pwd1+"&"+pwd2+"&"+member.getPwd()+"&"+ member.toString());
		}
		return "main";
	}
	@GetMapping("/logout")
	public void logout() {
	}
	
	/*센터소개 서비스 페이저*/
	@GetMapping("intro/about")
	public String introAbout() {
		return "센터소개/센터소개-인사말";
	}
	@GetMapping("intro/view")
	public String introView() {
		return "센터소개/센터소개-시설소개";
	}
	@GetMapping("intro/org")
	public String introOrg() {
		return "센터소개/센터소개-조직도";
	}
	@GetMapping("intro/map")
	public String introMap() {
		return "센터소개/센터소개-오시는길";
	}
	/*입소정보 서비스 페이저*/
	@GetMapping("info/enter_info")
	public String enterInfo() {
		return "입소정보/입소정보-입소안내";
	}
	@GetMapping("info/enter_cost")
	public String enterCost() {
		return "입소정보/입소정보-비용안내";
	}
	@GetMapping("info/enter_req")
	public String enterReq() {
		return "입소정보/입소정보-입소신청";
	}
	@GetMapping("info/enter_list")
	public String enterList() {
		return "입소정보/입소정보-명단";
	}
	
	/*프로그램 서비스 페이저*/
	@GetMapping("pgm/about")
	public String pgmAbout() {
		return "프로그램/프로그램-프로그램안내";
	}
	
	@GetMapping("pgm/schedule")
	public String pgmSchedule(PageVO vo,Model model) {
		Pageable page = vo.makePageable(0, "no");
//		Page<Schedule> result = bs.pager(page);
		Page<Schedule> result = bs.searching(vo, page);
		model.addAttribute("list",result);	
//		model.addAttribute("pager",new PageMaker(result));
		
		model.addAttribute("pager",new PageMaker(result));
		System.out.println(page);
		return "프로그램/프로그램-일정표";
	}
	@GetMapping("pgm/schedule/view")
	public String pgmScheduleBoardView(@RequestParam("no")int no, PageVO vo, Model model) {
		Pageable page = vo.makePageable(0, "no");
//		Page<Schedule> result = bs.pager(page);
		Page<Schedule> result = bs.searching(vo, page);
		model.addAttribute("list",result);	
//		model.addAttribute("pager",new PageMaker(result));
		model.addAttribute("content",bs.scheduleView(no));
		model.addAttribute("pager",new PageMaker(result));
		return "프로그램/프로그램-일정표세부";
	}

	@GetMapping("pgm/menu")
	public String pgmMenu() {
		return "프로그램/프로그램-식단표";
	}
	/*커뮤니티 서비스 페이저*/
	@GetMapping("comm/notice")
	public String commNotice() {
		return "커뮤니티/커뮤니티-공지사항";
	}
	@GetMapping("comm/news")
	public String commNews() {
		return "커뮤니티/커뮤니티-월간소식지";
	}
	@GetMapping("comm/photo")
	public String commPhoto() {
		return "커뮤니티/커뮤니티-포토갤러리";
	}
}
