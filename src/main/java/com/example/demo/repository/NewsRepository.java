package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.News;

public interface NewsRepository extends CrudRepository<News, Integer>{
	News findNewsByName(String name);
	@Query(value="select * from tb_news ORDER BY no DESC LIMIT 1",nativeQuery=true)
	List<News> findNewsRecentlyOne();
}
