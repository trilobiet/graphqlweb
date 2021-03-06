package com.trilobiet.graphqlweb.dao;

import java.util.List;
import java.util.Optional;

import com.trilobiet.graphqlweb.datamodel.Section;
import com.trilobiet.graphqlweb.datamodel.Topic;

/**
 * 
 * @author acdhirr
 *
 */
public interface TopicDao<T extends Topic> {

	List<T> list(Section section, String sort) throws DaoException;
	List<T> find(String searchterm, String sort) throws DaoException;
	List<T> find(FieldValueQuery q) throws DaoException;
	
	Optional<T> get(String id) throws DaoException;
	Optional<T> getBySlug(String slug) throws DaoException;
}
