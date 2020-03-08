package com.trilobiet.graphqlweb.dao;

import java.util.List;
import java.util.Optional;

import com.trilobiet.graphqlweb.datamodel.File;

public interface FileDao {

	Optional<File> get(String id) throws DaoException;
	List<File> getByName(String id) throws DaoException;
	List<File> find(FieldValueQuery q) throws DaoException;
}
