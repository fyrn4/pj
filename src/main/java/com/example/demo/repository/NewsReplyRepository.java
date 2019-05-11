package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.NewsReply;

public interface NewsReplyRepository extends CrudRepository<NewsReply, Integer>{
	List<NewsReply> findNewsReplyByBnoOrderByRnoDesc(int bno);
	@Query(value="select count(*) from tb_news_reply where bno=?1",nativeQuery=true)
	int newsReplyCount(int bno);
	
}

