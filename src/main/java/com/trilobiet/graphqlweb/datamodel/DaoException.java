package com.trilobiet.graphqlweb.datamodel;

public class DaoException extends Exception {

	private static final long serialVersionUID = 1L;

	public DaoException(Exception root) {
		super(root);
	}

}
