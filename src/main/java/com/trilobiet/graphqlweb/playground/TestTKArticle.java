package com.trilobiet.graphqlweb.playground;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;

import com.trilobiet.graphqlweb.dao.DaoException;
import com.trilobiet.graphqlweb.datamodel.Topic;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.article.ArticleImp;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.article.ArticleList;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.article.GenericArticleDao;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.service.html.HtmlArticleService;
import com.trilobiet.graphqlweb.markdown2html.Flexmark2HtmlFunction;

public class TestTKArticle {

	public static void main(String[] args) throws DaoException, IllegalStateException, MalformedURLException {
		
		String host = "http://localhost:1337/graphql";
		Topic atopic = new Topic();
		atopic.setName("Peer Review Policies ");
		
		TKArticleConverter<TKArticle> md2HtmlConverter = new TKArticleConverter<>(new Flexmark2HtmlFunction());

		System.out.println("TKArticle By Topic");

		GenericArticleDao<TKArticle> dao 
			= new GenericArticleDao<>(host, TKArticle.class, TKArticleList.class);
		
		List<TKArticle> articles2 = dao.list(atopic, "name:desc");
		System.out.println("    " + articles2);

		// --------------------
		
		HtmlArticleService<TKArticle> serv = new HtmlArticleService<TKArticle>(dao, md2HtmlConverter);
		List<TKArticle> q = serv.getArticlesByTopic(atopic);
		
		System.out.println("TKArticle List (via service)");
		System.out.println("    " + q);
		
		// --------------------
		
		System.out.println("ArticleImp By Topic");

		GenericArticleDao<ArticleImp> dao2 
		= new GenericArticleDao<>(host, ArticleImp.class, ArticleList.class);
	
		List<ArticleImp> articles3 = dao2.list(atopic, "name:desc");
	
		System.out.println("    " + articles3);

		// --------------------
		
		System.out.println("ArticleImp By id");
		
		String id = "5e6b8ec9de5c2c000c42292f";
		Optional<ArticleImp> art = dao2.get(id);
		System.out.println("    " + art);
		
		// --------------------
		
		System.out.println("TKArticle By id");
		
		Optional<TKArticle> art2 = dao.get(id);
		System.out.println("    " + art2);

		// --------------------
		
		System.out.println("TKArticle By slug");
		
		Optional<TKArticle> art3 = dao.getBySlug("7919930-aggregation-of-publications");
		System.out.println("    " + art3);
		
		// --------------------
		
		System.out.println("ArticleImp By slug");
		
		Optional<ArticleImp> art4 = dao2.getBySlug("7919930-aggregation-of-publications");
		System.out.println("    " + art4);
		
	}

}
