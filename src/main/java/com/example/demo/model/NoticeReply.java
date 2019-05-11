package com.example.demo.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name="tb_notice_reply")
@ToString
public class NoticeReply {
	
	@Id
	private int rno;
	private int bno;
	private String reply;
	private String replyer;
	@CreationTimestamp
	private Timestamp date;
	
}
