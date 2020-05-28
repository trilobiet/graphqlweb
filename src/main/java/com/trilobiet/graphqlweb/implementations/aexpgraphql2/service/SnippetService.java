package com.trilobiet.graphqlweb.implementations.aexpgraphql2.service;

import java.util.Optional;

import com.trilobiet.graphqlweb.dao.DaoException;
import com.trilobiet.graphqlweb.datamodel.Snippet;

public interface SnippetService<T extends Snippet> {
	
	Optional<T> getSnippet(String name) throws DaoException;
	
}