package com.example.demo.controller;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.Member;
import com.example.demo.model.News;
import com.example.demo.model.PageMaker;
import com.example.demo.model.PageVO;
import com.example.demo.model.PhotoPageVO;
import com.example.demo.model.Schedule;
import com.example.demo.model.ScheduleReply;
import com.example.demo.model.SignValidation;
import com.example.demo.repository.ScheduleRepository;
import com.example.demo.service.BoardService;
import com.example.demo.service.EmailService;
import com.example.demo.service.MemberService;
import com.example.demo.service.ReplyService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MainController {
	
	@Autowired
	MemberService ms;
	@Autowired
	BoardService bs;
	@Autowired
	ReplyService rs;
	@Autowired
	EmailService es;
	
	@GetMapping("/")
	public String mainPage(@AuthenticationPrincipal Principal principal, Model model) {
		model.addAttribute("news",bs.newsMainList());
		model.addAttribute("food",bs.foodMainList());
		model.addAttribute("notice",bs.noticeMainList());
		model.addAttribute("photo",bs.photoMainList());
		log.info("principal: {}", principal);	
		
		if(principal != null) {
			String userName = principal.getName();
		model.addAttribute("UserId",userName);
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
	@GetMapping("/forgot")
	public String forgotUser() {
		return "forgot";
	}
	@PostMapping("/forgot/id")
	public @ResponseBody List<String> testIdFind(@RequestParam String email) {
		System.out.println(email);
		List<String> list = ms.findId(email);
		System.out.println(list.toString());
		return list;
	}
	@PostMapping("/forgot/pwd")
	public @ResponseBody String testPwdFind(@RequestParam String userID, @RequestParam String userName, @RequestParam String userBirth) throws MessagingException {
		try {
			System.out.println("forgot service userId:"+userID);
			System.out.println("forgot service userName:"+userName);
			System.out.println("forgot service userBirth:"+userBirth);
			
			if(ms.idCheck(userID, userName, userBirth)) {
				String tempPwd = ms.tempPwd(userID, userName, userBirth);
				String recipient = ms.findPwd(userID);
				String content="<h1 style='color:lightcoral'>금빛주야간보호센터</h1><br>"
						+ "임시 비밀번호를 발급안내입니다. <br>"
						+ "임시비밀번호로 로그인 하신 후 내정보에서 비밀번호 변경을 부탁드립니다. <br><br>"
						+ "임시 비밀번호 [ <strong>"+ tempPwd + "</strong> ]";
				es.sendMail(recipient, content);
				System.out.println("recipientAddress:"+recipient);
				
			}else {
				return "등록된 회원정보가 없습니다. 다시 확인해주세요!";	
			}
		}catch (Exception e) {
			e.printStackTrace();
			return "등록된 아이이디가 없습니다. 다시 확인해주세요!";
		}
		return "임시비밀번호를 발송했습니다, 이메일을 확인해주세요.";
	}
	/*	@PostMapping("/signup")
	public String signUpPost(Member member, @RequestParam String pwd1, @RequestParam String pwd2) {
		if(pwd1.equals(pwd2)) {
			ms.signUp(member, pwd1, pwd2);
			System.out.println(pwd1+"&"+pwd2+"&"+member.getPwd()+"&"+ member.toString());
		}
		return "redirect:/";
	}*/
	
	@PostMapping("/signup/post")
	public @ResponseBody String testsign(Member member, @RequestParam String pwd1, @RequestParam String pwd2 ) {
		System.out.println("hi, im ajaxCheck Test!");
		System.out.println("member&"+"pwd1:"+pwd1+"pwd2"+pwd2);
		System.out.println("member&"+member.toString());
		
		String id = member.getId();
		String name = member.getName();
		String birth = member.getBirth();
		String phone = member.getPhone();
		String email = member.getEmail();
		
		if(!Pattern.matches("^[a-zA-Z0-9]{4,16}$",id)) {
			/*System.out.print("4~16글자 영문과 숫자로만 구성되었는지에 대한 체크:");
			System.out.println(Pattern.matches("^[a-zA-Z0-9]{4,16}$",id));*/
			return "아이디는 4~16자 영문과 숫자로만 구성되어야합니다.";
		}
		if( (!Pattern.matches("^\\s*(?:\\S\\s*){4,24}$", pwd1)) || (!pwd1.equals(pwd2)) ) {
			/*System.out.print(" 비밀번호 유효성 결과 : ");*/
			return "비밀번호를 확인해주세요.";
		}
		if(!Pattern.matches("^[가-힣]{2,8}$",name)) {
			/*System.out.print("이름에대한 유효성체크 : ");
			System.out.print(Pattern.matches("^[가-힣]{2,8}$",name));*/
			return "이름은 한글 정자 2~8글자 사이로 사용해주세요.";
		}
		if(!Pattern.matches("^[0-9]{6}$",birth)) {
			/*System.out.print("주민번호 앞자리 유효성체크 : ");
			System.out.println(Pattern.matches("^[0-9]{6}$",birth));*/
			return "올바른 생년월일을 입력해주세요.";
		}
		if(!Pattern.matches("^[0-9]{10,11}$",phone)) {
			/*System.out.print("전화번호 유효성체크 : ");
			System.out.println(Pattern.matches("^[0-9]{10,11}$",phone));*/
			return "올바른 전화번호를 사용해주세요.";
		}
		if(!Pattern.matches("[0-9a-zA-Z]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$", email)) {
			/*System.out.print("이메일 유효성체크 : ");
			System.out.println(Pattern.matches("[0-9a-zA-Z]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$", email));*/
			return "올바른 이메일형식이 아닙니다! 다시 확인해주세요.";
		}
		if(ms.idCheck(id)) {
			return "이미 사용중인 아이디입니다.";
		}
		member.setPwd(pwd1);
		System.out.println(member.toString());
		ms.signUptest(member);
		
		return "sign";
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
		Page<Schedule> result = bs.searching(vo, page);
		model.addAttribute("list",result);	
		model.addAttribute("pager",new PageMaker(result));
		System.out.println(page);
		return "프로그램/프로그램-일정표";
	}
	@GetMapping("pgm/schedule/view")
	public String pgmScheduleBoardView(@RequestParam("no")int no, PageVO vo, Model model) {
		Pageable page = vo.makePageable(0, "no");
		Page<Schedule> result = bs.searching(vo, page);
		model.addAttribute("list",result);	
		model.addAttribute("content",bs.scheduleView(no));
		model.addAttribute("pager",new PageMaker(result));
		
		
		model.addAttribute("replyCount",rs.replyCount(no));
		model.addAttribute("reply",rs.viewList(no));
		
		return "프로그램/프로그램-일정표세부";
	}

	@GetMapping("pgm/menu")
	public String pgmMenu(PageVO vo,Model model) {
		Pageable page = vo.makePageable(0,"no");
		model.addAttribute("list",bs.foodSearching(vo, page));
		model.addAttribute("pager", new PageMaker(bs.foodSearching(vo, page)));
		return "프로그램/프로그램-식단표";
	}
	@GetMapping("pgm/menu/view")
	public String pgmMenuView(@RequestParam("no")int no, PageVO vo, Model model) {
		Pageable page=vo.makePageable(0,"no");
		model.addAttribute("list",bs.foodSearching(vo, page));
		model.addAttribute("content", bs.foodView(no));
		model.addAttribute("pager", new PageMaker(bs.foodSearching(vo, page)));
		
		model.addAttribute("replyCount",rs.replyFoodCount(no));
		model.addAttribute("reply",rs.foodReplyList(no));
		return "프로그램/프로그램-식단표세부";
	}
	/*커뮤니티 서비스 페이저*/
	@GetMapping("comm/notice")
	public String commNotice(PageVO vo, Model model) {
		Pageable page=vo.makePageable(0,"no");
		model.addAttribute("list",bs.noticeSearching(vo, page));
		model.addAttribute("pager", new PageMaker(bs.noticeSearching(vo, page)));
		return "커뮤니티/커뮤니티-공지사항";
	}
	@GetMapping("comm/notice/view")
	public String commNoticeView(@RequestParam("no") int no, PageVO vo, Model model) {
		Pageable page=vo.makePageable(0,"no");
		model.addAttribute("list",bs.noticeSearching(vo, page));
		model.addAttribute("content", bs.noticeView(no));
		model.addAttribute("pager", new PageMaker(bs.noticeSearching(vo, page)));
		
		model.addAttribute("replyCount",rs.replyNoticeCount(no));
		model.addAttribute("reply",rs.noticeReplyList(no));
		return "커뮤니티/커뮤니티-공지사항세부";
	}
	@GetMapping("comm/news")
	public String commNews(PageVO vo, Model model) {
		Pageable page=vo.makePageable(0,"no");
		model.addAttribute("list",bs.newsSearching(vo, page));
		model.addAttribute("pager", new PageMaker(bs.newsSearching(vo, page)));
		return "커뮤니티/커뮤니티-월간소식지";
	}
	@GetMapping("comm/news/view")
	public String commNewsView(@RequestParam("no") int no, PageVO vo, Model model) {
		Pageable page=vo.makePageable(0,"no");
		model.addAttribute("list",bs.newsSearching(vo, page));
		model.addAttribute("content",bs.newsView(no));
		model.addAttribute("pager", new PageMaker(bs.newsSearching(vo, page)));
		
		model.addAttribute("replyCount",rs.replyNewsCount(no));
		model.addAttribute("reply",rs.newsReplyList(no));
		return "커뮤니티/커뮤니티-월간소식지세부";
	}
	@GetMapping("comm/photo")
	public String commPhoto(PhotoPageVO vo, Model model) {
		Pageable page=vo.makePageable(0,"no");
		model.addAttribute("list",bs.photoSearching(vo, page));
		model.addAttribute("pager", new PageMaker(bs.photoSearching(vo, page))); 
		return "커뮤니티/커뮤니티-포토갤러리";
	}
	@GetMapping("comm/photo/view")
	public String commPhotoView(@RequestParam("no") int no, PhotoPageVO vo, Model model) {
		Pageable page=vo.makePageable(0,"no");
		model.addAttribute("list",bs.photoSearching(vo, page));
		model.addAttribute("content",bs.photoView(no));
		model.addAttribute("pager", new PageMaker(bs.photoSearching(vo, page)));
		
		model.addAttribute("replyCount",rs.replyPhotoCount(no));
		model.addAttribute("reply",rs.photoReplyList(no));
		return "커뮤니티/커뮤니티-포토갤러리세부";
	} 
	
	@RequestMapping("myInfo")
	public String myInfo(@AuthenticationPrincipal Principal principal, Model model) {
		if(principal != null) {
			String userName = principal.getName();
		model.addAttribute("User",ms.memberOne(userName));
		}else {
			model.addAttribute("NotLogin","로그인이 필요합니다.");
		}
		return "myInfo";
	}
	@GetMapping("modifyInfo")
	public String modifyInfo(@AuthenticationPrincipal Principal principal, Model model) {
		if(principal != null) {
			String userName = principal.getName();
		model.addAttribute("User",ms.memberOne(userName));
		}else {
			model.addAttribute("NotLogin","로그인이 필요합니다.");
		}
		return "modifyInfo";
	}
	@PostMapping("modifyInfo")
	public @ResponseBody String modifyInfoPost(@RequestParam("name") String name,@RequestParam("phone") String phone,
			@RequestParam("email") String email, @AuthenticationPrincipal Principal principal ) {
		
		if(principal==null) {
			return "로그인이 필요합니다.";
		}		
		if(!Pattern.matches("^[가-힣]{2,8}$",name)) {
			return "이름은 한글 2~8자 사이로 사용해주세요.";
		}
		if(!Pattern.matches("^[0-9]{10,11}$",phone)) {
			return "올바른 휴대폰 번호를 입력해주세요.";
		}
		if(!Pattern.matches("[0-9a-zA-Z]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$", email)) {
			return "올바른 이메일형식이 아닙니다! 다시 확인해주세요.";
		}
		
		String id = principal.getName();
		
		ms.modify(id, name, phone, email);
		log.info("modifyInfo:"+name+"/"+phone+"/"+email);
		
		return "회원정보가 수정되었습니다.";
	}
	@GetMapping("modifyPwd")
	public String modifyPwd(@AuthenticationPrincipal Principal principal, Model model) {
		if(principal != null) {
			String userName = principal.getName();
		model.addAttribute("User",ms.memberOne(userName));
		}else {
			model.addAttribute("NotLogin","로그인이 필요합니다.");
		}
		return "modifypwd";
	}
	@PostMapping("modifyPwd")
	public @ResponseBody String modifyPwdPost(@AuthenticationPrincipal Principal principal, Model model,
			@RequestParam("pwd") String pwd, @RequestParam("newPwd") String newPwd, @RequestParam("newPwd2") String newPwd2) {
		if(principal==null) {
			return "로그인이 필요합니다.";
		}
		if(!ms.pwdCheck(principal.getName(),pwd)) {
			return "기존 비밀번호가 틀립니다.";
		}
		if( (!Pattern.matches("^\\s*(?:\\S\\s*){4,24}$", newPwd)) || (!newPwd.equals(newPwd2)) ) {
			return "새 비밀번호를 확인해주세요. 비밀번호는 4~24자 사이로 입력해주세요.";
		}
		
		ms.modifyPwd(principal.getName(), newPwd2);
		
		return "비밀번호가 변경되었습니다.";
	}
	@GetMapping("deleteUser")
	public String deleteUser(@AuthenticationPrincipal Principal principal, Model model) {
		if(principal != null) {
			String userName = principal.getName();
		model.addAttribute("User",ms.memberOne(userName));
		}else {
			model.addAttribute("NotLogin","로그인이 필요합니다.");
		}
		return "deleteUser"; 
	}
	@PostMapping("deleteUser")
	public @ResponseBody String deleteUserPost(@AuthenticationPrincipal Principal principal, Model model, @RequestParam("pwd")String pwd) {
		if(principal==null) {
			return "로그인이 필요합니다.";
		}
		if(!ms.pwdCheck(principal.getName(),pwd)) {
			return "비밀번호가 틀립니다.";
		}
		ms.deleteUser(principal.getName());
		return "계정이 탈퇴되었습니다."; 
	}
}
