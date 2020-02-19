package com.nc.submit_article_services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.commons.io.IOUtils;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nc.document_handlers.PDFHandler;
import com.nc.elasticsearch_model.ArticleIndexingUnit;
//import com.nc.elasticsearch_repository.ArticleEsRepository;
import com.nc.model.Article;
import com.nc.model.Coauthor;
import com.nc.model.ScientificArea;
import com.nc.repository.ArticleRepository;
import com.nc.repository.ScientificAreaRepository;

@Service
public class IndexingService implements JavaDelegate {

	@Autowired
	ArticleRepository articleRepository;

	@Autowired
	RestHighLevelClient client;
	
	@Autowired
	ScientificAreaRepository sciAreaRepository;
	
	@Autowired
	PDFHandler pdfHandler;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {

		long articleId = Long.parseLong(execution.getVariable("articleId").toString());
		Article article = articleRepository.findOneById(articleId);
		
		article.setPdfFileName(execution.getVariable("fileName").toString());
		
		File file = new File("pdf/" +article.getPdfFileName());
		File outputFile = new File("acceptedPdfs/" +article.getPdfFileName());
		
		FileInputStream fis = new FileInputStream(file);
		FileOutputStream fos = new FileOutputStream(outputFile);

		byte[] content = IOUtils.toByteArray(fis);
		fos.write(content);
			
		fis.close();
		
		fos.flush();
		fos.close();

		ArticleIndexingUnit articleEs = new ArticleIndexingUnit();
		
		articleEs.setId(article.getId());
		articleEs.setMagazineName(article.getMagazine().getName());
		articleEs.setTitle(article.getTitle());
		articleEs.setKeyWords(article.getKeyWords());
		articleEs.setScientificArea(article.getScientificArea());
		
		String coauthors = "";
		for(Coauthor co : article.getCoauthors()) {
			coauthors += co.getName() + " " + co.getSurname() + ", ";
		}
		articleEs.setCoauthors(coauthors);
		
		String scientificAreaId = execution.getVariable("scientificArea").toString();
		ScientificArea sciArea = sciAreaRepository.findOneById(scientificAreaId);
		
		articleEs.setScientificArea(sciArea.getName());
		
		articleEs.setContent(pdfHandler.getText(outputFile));

		IndexRequest indexRequest = new IndexRequest("articles_index");
		indexRequest.id(Long.toString(article.getId()));
		
		ObjectMapper mapper = new ObjectMapper();
		
		indexRequest.source(mapper.writeValueAsString(articleEs), XContentType.JSON);
		
		client.index(indexRequest, RequestOptions.DEFAULT);
	}

}
