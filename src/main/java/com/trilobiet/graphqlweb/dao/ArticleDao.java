package com.trilobiet.graphqlweb.dao;

import java.util.List;
import java.util.Optional;

import com.trilobiet.graphqlweb.datamodel.Article;
import com.trilobiet.graphqlweb.datamodel.Category;
import com.trilobiet.graphqlweb.datamodel.Topic;

/**
 * 
 * @author acdhirr
 *
 */
public interface ArticleDao {

	List<Category> listCategories() throws DaoException;
	List<Article> list(Topic topic, String sort) throws DaoException;
	List<Article> list(Category category, String sort) throws DaoException;
	List<Article> find(String searchterm, String sort) throws DaoException;
	Optional<Article> get(String id) throws DaoException;
	Optional<Article> getBySlug(String slug) throws DaoException;
}
