package com.nc.dto;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import com.nc.model.Article;
import com.nc.model.Coauthor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDTO {

	private long id;
	private String title;
	private String magazineName;
	private String sceintificArea;
	private String authors;
	private String contentHighlight;
	private float price;
	private boolean bought;
	private String download;
	
	public ArticleDTO(Article article) {
		
		this.id = article.getId();
		this.title = article.getTitle();
		this.magazineName = article.getMagazine().getName();
		this.price = (float) 10.5;
		this.bought = article.getMagazine().isOpenAccess();
		this.authors = article.getAuthor().getName() + " " + article.getAuthor().getSurname() +", ";
		
		for(Coauthor co : article.getCoauthors()) {
			if(article.getCoauthors().indexOf(co) == (article.getCoauthors().size() - 1))	this.authors += co.getName() + " " + co.getSurname();
			else this.authors += co.getName() + " " + co.getSurname() +", ";
		}	
		
		File file = new File("acceptedPdfs/"+article.getPdfFileName());
		byte[] encoded = null;
		try {
			encoded = Base64.encodeBase64(FileUtils.readFileToByteArray(file));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String startString = "data:application/pdf;base64,";
		String dataStrign = new String(encoded, StandardCharsets.US_ASCII);
		this.download = startString + StringUtils.remove(dataStrign, "data/application/pdf/base64/");	
	}
	
}
