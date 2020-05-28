package com.trilobiet.graphqlweb.implementations.aexpgraphql2.service;

import java.util.List;
import java.util.Optional;

import com.trilobiet.graphqlweb.dao.DaoException;
import com.trilobiet.graphqlweb.datamodel.File;

public interface FileService<T extends File> {
	
	Optional<T> get(String id) throws DaoException;
	// Files may share names
	List<T> getByName(String name) throws DaoException;
	Optional<T> getFirstWithName(String name) throws DaoException;
	List<T> getByFieldValue(String field, String value) throws DaoException;
}