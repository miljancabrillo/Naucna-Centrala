package com.nc.submit_article_services;

import java.util.ArrayList;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nc.model.Review;
import com.nc.repository.UserDetailsRepository;

@Service
public class SaveReviewService implements JavaDelegate {

	@Autowired
	UserDetailsRepository udRepository;
	
	@SuppressWarnings("unchecked")
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub

		ArrayList<Review> reviews = (ArrayList<Review>) execution.getVariable("reviews");
		ArrayList<Review> reviewsForAuthor = (ArrayList<Review>) execution.getVariable("reviewsForAuthor");
	
		if(reviews == null) {
			reviews = new ArrayList<>();
		}
		
		String reviewerUsername = execution.getVariable("reviewerId").toString();
		String status = execution.getVariable("articleStatus").toString();
		String review = execution.getVariable("review").toString();
		String comment = execution.getVariable("editorComment").toString();
		String fileName = execution.getVariable("fileName").toString();
		
		Review rev = new Review();
		rev.setReviewer(udRepository.findUserDetailsByUsername(reviewerUsername));
		rev.setReview(review);
		rev.setCommentForEditor(comment);
		rev.setStatus(status);
		rev.setFileName(fileName);
		rev.setForEditor(true);
		
		Review rev2 = new Review();
		rev2.setReviewer(udRepository.findUserDetailsByUsername(reviewerUsername));
		rev2.setReview(review);
		rev2.setCommentForEditor(comment);
		rev2.setStatus(status);
		rev2.setFileName(fileName);
		rev2.setForEditor(false);
		
		reviews.add(rev);
		reviewsForAuthor.add(rev2);
		
		execution.setVariable("reviews", reviews);
		execution.setVariable("reviewsForAuthor", reviewsForAuthor);


	}

}
