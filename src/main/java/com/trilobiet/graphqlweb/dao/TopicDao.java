package com.trilobiet.graphqlweb.dao;

import java.util.Collection;

import com.trilobiet.graphqlweb.datamodel.Category;
import com.trilobiet.graphqlweb.datamodel.DaoException;
import com.trilobiet.graphqlweb.datamodel.Section;
import com.trilobiet.graphqlweb.datamodel.Topic;

public interface TopicDao {

	Collection<Section> listSections() throws DaoException;
	Collection<Topic> list(Section section, String sort) throws DaoException;
	Collection<Topic> list(Category category, String sort) throws DaoException;
	Collection<Topic> find(String where, String sort) throws DaoException;
	Topic get(String id) throws DaoException;
	
}
