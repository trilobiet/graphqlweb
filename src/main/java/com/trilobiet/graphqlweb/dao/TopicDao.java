package com.trilobiet.graphqlweb.dao;

import java.util.List;
import java.util.Optional;

import com.trilobiet.graphqlweb.datamodel.Article;
import com.trilobiet.graphqlweb.datamodel.Section;
import com.trilobiet.graphqlweb.datamodel.Topic;

/**
 * 
 * @author acdhirr
 *
 */
public interface TopicDao {

	List<Section> listSections() throws DaoException;
	
	Optional<Section> getSectionBySlug(String slug) throws DaoException;
	
	List<Topic> list(Section section, String sort) throws DaoException;
	List<Topic> find(String searchterm, String sort) throws DaoException;
	List<Topic> find(FieldValueQuery q) throws DaoException;
	
	Optional<Topic> get(String id) throws DaoException;
	Optional<Topic> getBySlug(String slug) throws DaoException;
}
