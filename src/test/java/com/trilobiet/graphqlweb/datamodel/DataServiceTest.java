package com.trilobiet.graphqlweb.datamodel;

import static org.mockito.Mockito.mock;

import org.junit.Test;

import com.trilobiet.graphqlweb.service.ArticleService;

/**
 * 
 * @author acdhirr
 *
 */
public class DataServiceTest {
	
	Article article = mock(Article.class);
	Topic topic = mock(Topic.class);
	ArticleService ds = mock(ArticleService.class);
	
	@Test
	public void testArticle() {
		
	}
	
}
