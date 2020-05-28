package com.trilobiet.graphqlweb.implementations.aexpgraphql2.service;

import java.util.List;
import java.util.Optional;

import com.trilobiet.graphqlweb.dao.DaoException;
import com.trilobiet.graphqlweb.datamodel.Section;

public interface SectionService<T extends Section> {

	List<T> getSections() throws DaoException;
	Optional<T> getSectionBySlug(String slug) throws DaoException;
}