package com.nc.submit_article_services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nc.model.Article;
import com.nc.repository.ArticleRepository;

@Service
public class AssignDOIAndSaveArticleService implements JavaDelegate {
	
	@Autowired
	ArticleRepository articleRepository;

	@Override
	public void execute(DelegateExecution execution) throws IOException{
		// TODO Auto-generated method stub
		
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

	}

}
