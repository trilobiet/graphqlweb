package com.trilobiet.graphqlweb.dao;

import java.util.List;
import java.util.Optional;

import com.trilobiet.graphqlweb.datamodel.Snippet;

public interface SnippetDao {

	List<Snippet> list() throws DaoException;
	Optional<Snippet> get(String id) throws DaoException;
	Optional<Snippet> getByName(String id) throws DaoException;
}
