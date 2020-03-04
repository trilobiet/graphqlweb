package com.trilobiet.graphqlweb.datamodel;

import java.text.Normalizer;
import java.util.Comparator;

import com.trilobiet.graphqlweb.datamodel.SortableArticle;

public class ArticleComparator implements Comparator<SortableArticle> {

	@Override
	public int compare(SortableArticle a1, SortableArticle a2) {
		
		// sort by topicLead, then index, then title
		Comparator<SortableArticle> c = 
				// negate, because true must be on top
				Comparator.comparing(article -> !article.isTopicLead()); 
		c = c.thenComparing( article -> article.getIndex() );
		c = c.thenComparing( article -> normalized(article.getTitle() ) );
		// add more comparisons if needed

		return c.compare(a1, a2);
	}
	
	/*
	 * Normalize special (accented) characters
	 */
	private String normalized(String s) {
		
		if (s != null) return Normalizer.normalize(s, Normalizer.Form.NFD);
		else return "";
	}

}
