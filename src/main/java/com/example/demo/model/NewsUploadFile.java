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
@Table(name="tb_news_file")
public class NewsUploadFile {
	@Id
	private int no;

	private int bno;
	private String fname;
	private String ofname;
	private Long size;
	private String path;
}
