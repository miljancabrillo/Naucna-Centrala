package com.nc.submit_article_services;

import java.util.ArrayList;
import java.util.HashMap;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nc.model.Article;
import com.nc.model.Coauthor;
import com.nc.model.Magazine;
import com.nc.model.ScientificArea;
import com.nc.model.UserDetails;
import com.nc.repository.ArticleRepository;
import com.nc.repository.MagazineRepository;
import com.nc.repository.UserDetailsRepository;

@Service
public class SaveArticleDataService implements JavaDelegate {

	@Autowired
	ArticleRepository articleRepository;
	
	@Autowired
	MagazineRepository magazineRepository;

	@Autowired
	UserDetailsRepository udRepository;
	
	@SuppressWarnings("unchecked")
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		
		long magazineId = Long.parseLong(execution.getVariable("selectedMagazineId").toString());
		Magazine magazien = magazineRepository.findOneById(magazineId);
		
		String authhorUsername = execution.getVariable("AuthorId").toString();
		UserDetails author = udRepository.findUserDetailsByUsername(authhorUsername);
		
		Article article = new Article();
		article.setTitle(execution.getVariable("title").toString());
		article.setCoauthors((ArrayList<Coauthor>) execution.getVariable("coauthors"));
		article.setKeyWords(execution.getVariable("keyWords").toString());
		article.setArticleAbstract(execution.getVariable("abstract").toString());
		article.setPdfFileName(execution.getVariable("fileName").toString());
		article.setMagazine(magazien);
		article.setAuthor(author);
		article = articleRepository.saveAndFlush(article);
		
		
		execution.setVariable("articleId", article.getId());

		
		String editorUsername = "";
		
		for (HashMap.Entry<ScientificArea, UserDetails> entry : magazien.getScientificAreaEditorMap().entrySet()) {
		    ScientificArea area = entry.getKey();
		    UserDetails editor = entry.getValue();
		    if(area.getId().equals(execution.getVariable("scientificArea").toString())) editorUsername = editor.getUsername();
		}
		
		if(!editorUsername.equals("")) {
			execution.setVariable("sciAreaEditorId", editorUsername);
			execution.setVariable("selectReviewersAssaigneeId", editorUsername);
			execution.setVariable("sciAreaEditorFound", true);
		}else {
			execution.setVariable("selectReviewersAssaigneeId", execution.getVariable("chiefEditorId"));
			execution.setVariable("sciAreaEditorFound", false);
		}
		
		
	}

}
