package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Food;
import com.example.demo.model.News;
import com.example.demo.model.QFood;
import com.example.demo.model.QNews;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public interface NewsRepository extends CrudRepository<News, Integer>,QuerydslPredicateExecutor<News>{
	News findNewsByName(String name);
	@Query(value="select * from tb_news ORDER BY no DESC LIMIT 1",nativeQuery=true)
	List<News> findNewsRecentlyOne();
	News findNewsByNo(int no);
	
	default Predicate makePredicate(String type, String keyword) {
		BooleanBuilder builder = new BooleanBuilder();
		QNews news = QNews.news;
		
		builder.and(news.no.gt(0));
		
		if(type==null) {
		return builder;
		}
		switch(type) {
		case "tc": builder.and(news.title.like("%"+keyword+"%").or(news.content.like("%"+keyword+"%")));
		}
		return builder;
	}
	
}
