package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Notice;
import com.example.demo.model.QNotice;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public interface NoticeRepository extends CrudRepository<Notice, Integer>,QuerydslPredicateExecutor<Notice>{
	Notice findNoticeByName(String name);
	@Query(value="select * from tb_notice ORDER BY no DESC LIMIT 5",nativeQuery=true)
	List<Notice> findNoticeRecentlyList();
	Notice findNoticeByNo(int bno);
	
	default Predicate makePredicate(String type, String keyword) {
		BooleanBuilder builder = new BooleanBuilder();
		QNotice notice = QNotice.notice;
		
		builder.and(notice.no.gt(0));
		
		if(type==null) {
		return builder;
		}
		switch(type) {
		case "tc": builder.and(notice.title.like("%"+keyword+"%").or(notice.content.like("%"+keyword+"%")));
		}
		return builder;
	}
}
