package com.example.demo.model;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.service.MemberService;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignValidation {
	
	@Autowired MemberService ms;
	
	public String id;
	public String pwd1;
	public String pwd2;
	public String name;
	public String birth;
	public String phone;
	public String email;
	
	public String vaildate(String id,String pwd1,String pwd2,String name,String birth,String phone,String email) {
		
		if(!Pattern.matches("^[a-zA-Z0-9]{4,16}$",id)) {
			/*System.out.print("4~16글자 영문과 숫자로만 구성되었는지에 대한 체크:");
			System.out.println(Pattern.matches("^[a-zA-Z0-9]{4,16}$",id));*/
			return "ID유효성 검사 실패!";
		}
		if( (!Pattern.matches("^\\s*(?:\\S\\s*){4,24}$", pwd1)) || (!pwd1.equals(pwd2)) ) {
			/*System.out.print(" 비밀번호 유효성 결과 : ");*/
			return "비밀번호를 확인해주세요.";
		}
		if(!Pattern.matches("^[가-힣]{2,8}$",name)) {
			/*System.out.print("이름에대한 유효성체크 : ");
			System.out.print(Pattern.matches("^[가-힣]{2,8}$",name));*/
			return "이름 유효성 검사 실패";
		}
		if(!Pattern.matches("^[0-9]{6}$",birth)) {
			/*System.out.print("주민번호 앞자리 유효성체크 : ");
			System.out.println(Pattern.matches("^[0-9]{6}$",birth));*/
			return "생년월일 유효성 검사 실패";
		}
		if(!Pattern.matches("^[0-9]{10,11}$",phone)) {
			/*System.out.print("전화번호 유효성체크 : ");
			System.out.println(Pattern.matches("^[0-9]{10,11}$",phone));*/
			return "전화번호 유효성 실패";
		}
		if(!Pattern.matches("[0-9a-zA-Z]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$", email)) {
			/*System.out.print("이메일 유효성체크 : ");
			System.out.println(Pattern.matches("[0-9a-zA-Z]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$", email));*/
			return "올바른 이메일형식이 아닙니다! 다시 확인해주세요.";
		}
		
		return "회원가입이 완료되었습니다.";
	}
	
}
