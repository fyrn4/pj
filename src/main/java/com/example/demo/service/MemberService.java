package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.Member;
import com.example.demo.repository.MemberRepository;

@Service
public class MemberService implements UserDetailsService {
	
	@Autowired
	MemberRepository mr;
	
	@Autowired
	PasswordEncoder pe;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		mr.findById(username).ifPresent(member -> System.out.println("dto"+member));
		Member member = mr.findById(username).get();
		List<GrantedAuthority> authority = new ArrayList<>();
		authority.add(new SimpleGrantedAuthority("ROLE_"+member.getRole()));
		User user = new User(username, member.getPwd(), authority);
		return user;
	}
	public Member memberOne(String id) {
		Member member = mr.findById(id).get();
		return member;
	}
	public List<Member> memberlist(){
		String role = "USER";
		return mr.findByRole(role);
	}
	public boolean idCheck(String id) {
		System.out.println("memberCheck:"+mr.existsById(id));
		return mr.existsById(id);
	}
	public boolean idCheck(String id,String name,String birth) {
		System.out.println("memberCheckWithIdNameBirth:"+mr.existsByIdAndNameAndBirth(id,name,birth));
		return mr.existsByIdAndNameAndBirth(id,name,birth);
	}
	public String tempPwd(String id,String name, String birth) {
		Member m = new Member();
		Random rand = new Random();
		String numStr = "";
		
		for(int i=0;i<6;i++) {
			String ran = Integer.toString(rand.nextInt(10));
			numStr += ran;
		}
		
		m = mr.findByIdAndNameAndBirth(id, name, birth);
		System.out.println(m.toString());
		m.setPwd(pe.encode(numStr));
		mr.save(m);
		
		return numStr;
	}
	public void signUp(Member member, String pwd1, String pwd2) {
			member.setPwd(pe.encode(pwd1));
			member.setEnabled(true);
			member.setRole("USER");
			mr.save(member);
	}
	public void signUptest(Member member) {
		member.setPwd(pe.encode(member.getPwd()));
		member.setEnabled(true);
		member.setRole("USER");
		mr.save(member);
	}
	
	public List<String> findId(String email){
		/*List<Member> users = mr.forEmailUsers(email);*/
		List<String> users = mr.usersid(email);
		List<String> hideUser = new ArrayList<>();
		for(String hide : users) {
			hideUser.add("**"+hide.substring(2));
		}
		return hideUser;
	}
	
	public String findPwd(String id) throws Exception{
		String email="empty";
		try {
			Optional<Member> member = mr.findById(id);
			if(member.empty() != null) {
				email = member.get().getEmail();
				return email;
			}else {
				return email;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return email;
	}
	
	public void modify(String id, String name, String phone, String email) {
		Member member = memberOne(id);
		
		member.setName(name);
		member.setPhone(phone);
		member.setEmail(email);
		
		mr.save(member);
	}
	public boolean pwdCheck(String id, String pwd) {
		Member member = memberOne(id);
		return pe.matches(pwd, member.getPwd());
	}
	public void modifyPwd(String id, String pwd) {
		Member member = memberOne(id);
		member.setPwd(pe.encode(pwd));
		mr.save(member);
	}
	public void deleteUser(String id) {
		mr.deleteById(id);
	}
}
