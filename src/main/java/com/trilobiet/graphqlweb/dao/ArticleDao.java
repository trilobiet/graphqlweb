package com.trilobiet.graphqlweb.dao;

import java.util.List;
import java.util.Optional;

import com.trilobiet.graphqlweb.datamodel.Category;
import com.trilobiet.graphqlweb.datamodel.SortableArticle;
import com.trilobiet.graphqlweb.datamodel.Topic;

/**
 * 
 * @author acdhirr
 *
 */
public interface ArticleDao<T extends SortableArticle> {

	List<Category> listCategories() throws DaoException;
	
	List<T> list(Topic topic, String sort) throws DaoException;
	List<T> list(Category category, String sort) throws DaoException;
	List<T> find(String searchterm, String sort) throws DaoException;
	List<T> find(FieldValueQuery q) throws DaoException;
	
	Optional<T> get(String id) throws DaoException;
	Optional<T> getBySlug(String slug) throws DaoException;
}
