package com.trilobiet.graphqlweb.implementations.aexpgraphql2.service;

import java.util.List;
import java.util.Optional;

import com.trilobiet.graphqlweb.dao.DaoException;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.topic.TopicImp;

public interface TopicService<T extends TopicImp> {

	Optional<T> getTopicBySlug(String slug) throws DaoException;
	List<T> getByFieldValue(String field, String value) throws DaoException;
	List<T> getByFieldContainsValue(String field, String value) throws DaoException;
	
}