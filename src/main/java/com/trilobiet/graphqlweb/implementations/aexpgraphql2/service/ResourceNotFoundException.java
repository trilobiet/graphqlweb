package com.trilobiet.graphqlweb.implementations.aexpgraphql2.service;

import com.trilobiet.graphqlweb.dao.DaoException;

public class ResourceNotFoundException extends DaoException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(Exception root) {
		super(root);
	}

	public ResourceNotFoundException(String message) {
		super(message);
	}

	public ResourceNotFoundException() {
		super();
	}
}
