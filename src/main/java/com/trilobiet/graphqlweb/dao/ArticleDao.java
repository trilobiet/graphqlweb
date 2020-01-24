package com.trilobiet.graphqlweb.dao;

import java.util.Collection;

import com.trilobiet.graphqlweb.datamodel.Article;
import com.trilobiet.graphqlweb.datamodel.Category;
import com.trilobiet.graphqlweb.datamodel.DaoException;
import com.trilobiet.graphqlweb.datamodel.Topic;

public interface ArticleDao {

	Collection<Category> listCategories() throws DaoException;
	Collection<Article> list(Topic topic, String sort) throws DaoException;
	Collection<Article> list(Category category, String sort) throws DaoException;
	Collection<Article> find(String searchterm, String sort) throws DaoException;
	Article get(String id) throws DaoException;
}
