package com.nc.elasticsearch_repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.nc.elasticsearch_model.ArticleIndexingUnit;

public interface ArticleEsRepository extends ElasticsearchRepository<ArticleIndexingUnit, Long>{

}
