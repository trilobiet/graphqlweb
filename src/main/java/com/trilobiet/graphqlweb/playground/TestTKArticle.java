package com.trilobiet.graphqlweb.playground;

import java.net.MalformedURLException;
import java.util.List;

import com.trilobiet.graphqlweb.dao.DaoException;
import com.trilobiet.graphqlweb.datamodel.Topic;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.article.GenericArticleDao;

public class TestTKArticle {

	public static void main(String[] args) throws DaoException, IllegalStateException, MalformedURLException {
		
		String host = "https://oapen-cms.trilobiet.eu/graphql";
		Topic atopic = new Topic();
		atopic.setSlug("16110258-1-screwdrivers");
		GenericArticleDao<TKArticle> dao = new GenericArticleDao<>(host, TKArticle.class, TKArticleList.class);
		
		List<TKArticle> arts = dao.list(atopic, "name:desc");
		System.out.println(arts);
		
		
	}

}
