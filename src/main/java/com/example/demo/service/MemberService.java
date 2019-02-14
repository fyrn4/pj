package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

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
	public Member memberlist(String id) {
		Member member = mr.findById(id).get();
		return member;
	}
	
	public void signUp(Member member, String pwd1, String pwd2) {
			member.setPwd(pe.encode(pwd1));
			member.setEnabled(true);
			member.setRole("USER");
			mr.save(member);
	}

}
