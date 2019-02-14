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
@ToString
@Entity
@Table(name="tb_schedule")
public class Schedule {
	
	@Id
	private int no;
	private String title;
	private String name;
	private String content;
	@CreationTimestamp
	private Timestamp date;
	private int count;
}
