package com.trilobiet.graphqlweb.implementations.aexpgraphql2.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.trilobiet.graphqlweb.dao.DaoException;
import com.trilobiet.graphqlweb.datamodel.ArticleOutline;
import com.trilobiet.graphqlweb.datamodel.Category;
import com.trilobiet.graphqlweb.datamodel.Topic;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.article.ArticleImp;

public interface ArticleService<T extends ArticleImp> {

	List<Category> getCategories() throws DaoException;
	List<T> getArticlesByTopic(Topic topic) throws DaoException;
	Optional<T> getArticleBySlug(String slug) throws DaoException;
	List<T> getByFieldValue(String field, String value) throws DaoException;
	List<T> getByFieldContainsValue(String field, String value) throws DaoException;
	Set<ArticleOutline> getLinked(T article) throws DaoException;
}