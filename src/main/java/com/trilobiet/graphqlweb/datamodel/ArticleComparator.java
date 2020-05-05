package com.trilobiet.graphqlweb.datamodel;

import java.util.Comparator;

public class ArticleComparator implements NormalizedComparator<SortableArticle> {

	@Override
	public int compare(SortableArticle a1, SortableArticle a2) {
		
		// sort by topicLead, then index, then title
		Comparator<SortableArticle> c = 
				// negate, because true must be on top
				Comparator.comparing(article -> !article.isTopicLead()); 
		c = c.thenComparing( article -> article.getIndex() );
		c = c.thenComparing( article -> normalized(article.getTitle().trim() ), String.CASE_INSENSITIVE_ORDER );
		// add more comparisons if needed

		return c.compare(a1, a2);
	}

}
