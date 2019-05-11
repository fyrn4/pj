package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.NoticeReply;

public interface NoticeReplyRepository extends CrudRepository<NoticeReply, Integer>{
	List<NoticeReply> findNoticeReplyByBnoOrderByRnoDesc(int bno);
	@Query(value="select count(*) from tb_notice_reply where bno=?1",nativeQuery=true)
	int noticeReplyCount(int bno);
	
}
