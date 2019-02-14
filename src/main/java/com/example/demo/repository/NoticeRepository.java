package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Notice;

public interface NoticeRepository extends CrudRepository<Notice, Integer>{
	Notice findNoticeByName(String name);
	@Query(value="select * from tb_notice ORDER BY no DESC LIMIT 5",nativeQuery=true)
	List<Notice> findNoticeRecentlyList();
}
