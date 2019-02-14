package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.example.demo.model.Member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SecurityUser extends User {
	
	private static String ROLE_PREFIX = "ROLE_";
	private static Member member;

	
	public SecurityUser(Member member) {
		super(member.getId(), member.getPwd(),roles());	
		this.member = member;
	}
	
	private static List<GrantedAuthority> roles(){
		List<GrantedAuthority> authority = new ArrayList<>();
		authority.add(new SimpleGrantedAuthority(ROLE_PREFIX+member.getRole()));
		System.out.println(authority);
		return authority;
	}
	
}
