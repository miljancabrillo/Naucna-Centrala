package com.nc.elasticsearch_model;


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
public class ArticleIndexingUnit {

	private long id;
		
	private String magazineName;
	
	private String title;
		
	private String coauthors;
		
	private String keyWords;
		
	private String content;
	
	private String scientificArea;
		
}
