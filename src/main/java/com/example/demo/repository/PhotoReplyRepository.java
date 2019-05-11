package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.PhotoReply;

public interface PhotoReplyRepository extends CrudRepository<PhotoReply, Integer>{
	List<PhotoReply> findPhotoReplyByBnoOrderByRnoDesc(int bno);
	@Query(value="select count(*) from tb_photo_reply where bno=?1",nativeQuery=true)
	int photoReplyCount(int bno);
	
}
