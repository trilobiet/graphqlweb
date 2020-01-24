package com.trilobiet.graphqlweb.service;

import java.util.Collection;

import com.trilobiet.graphqlweb.dao.ArticleDao;
import com.trilobiet.graphqlweb.datamodel.Category;
import com.trilobiet.graphqlweb.datamodel.DaoException;
import com.trilobiet.graphqlweb.implementations.aexpgraphql.GraphQLArticleDao;

public class ArticleService {

	private ArticleDao dao;
	
	public ArticleService(ArticleDao dao) {
		this.dao = dao;
	}

	
	public Collection<Category> getCategories() throws DaoException {
		
		return dao.listCategories();
	}
	
	

	public static void main(String[] args) {

		ArticleService s = new ArticleService( new GraphQLArticleDao("http://localhost:1337/graphql") );
		
		try {
			Collection<Category> list = s.getCategories();
			System.out.println(list);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		
	}

}
