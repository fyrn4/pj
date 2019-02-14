package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="tb_member")
public class Member {
	@Id
	private String id;

	private String pwd;
	private String name;
	private String birth;
	private String phone;
	private String email;
	private boolean enabled;
	private String role;
}