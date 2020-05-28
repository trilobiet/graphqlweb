package com.trilobiet.graphqlweb.dao;

import java.util.List;
import java.util.Optional;

import com.trilobiet.graphqlweb.datamodel.Section;

/**
 * 
 * @author acdhirr
 *
 */
public interface SectionDao<T extends Section> {

	List<T> list() throws DaoException;

	Optional<T> get(String id) throws DaoException;
	Optional<T> getBySlug(String slug) throws DaoException;
}
