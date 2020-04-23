package com.trilobiet.graphqlweb.dao;

import java.util.List;
import java.util.Optional;

import com.trilobiet.graphqlweb.datamodel.File;

public interface FileDao<T extends File> {

	Optional<T> get(String id) throws DaoException;
	List<T> getByName(String id) throws DaoException;
	List<T> find(FieldValueQuery q) throws DaoException;
}
