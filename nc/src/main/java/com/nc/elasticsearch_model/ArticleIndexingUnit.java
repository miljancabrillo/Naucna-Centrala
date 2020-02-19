package com.nc.elasticsearch_model;

import javax.persistence.Id;

/*import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;*/

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Document(indexName = "udd_index", type = "article")
public class ArticleIndexingUnit {

	//@Id	
	//@Field(type = FieldType.Integer, store = true)
	private long id;
	
	//@Field(type = FieldType.Text, analyzer = "english", searchAnalyzer = "english", store = true)
	private String magazineName;
	
	//@Field(type = FieldType.Text, analyzer = "english", searchAnalyzer = "english", store = true)
	private String title;
		
	//@Field(type = FieldType.Text, analyzer = "english", searchAnalyzer = "english", store = true)
	private String coauthors;
		
	//@Field(type = FieldType.Text, analyzer = "english", searchAnalyzer = "english", store = true)
	private String keyWords;
		
	//@Field(type = FieldType.Text, analyzer = "english", searchAnalyzer = "english", store = true)
	private String content;
	
	//@Field(type = FieldType.Text, analyzer = "english", searchAnalyzer = "english", store = true)
	private String scientificArea;
		
}
