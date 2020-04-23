package com.trilobiet.graphqlweb.dao;

import java.util.List;
import java.util.Optional;

import com.trilobiet.graphqlweb.datamodel.Snippet;

public interface SnippetDao<T extends Snippet> {

	List<T> list() throws DaoException;
	Optional<T> get(String id) throws DaoException;
	Optional<T> getByName(String id) throws DaoException;
}
