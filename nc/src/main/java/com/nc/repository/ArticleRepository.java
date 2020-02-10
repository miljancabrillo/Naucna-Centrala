package com.nc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nc.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {

	public Article findOneById(long id);
	
}

