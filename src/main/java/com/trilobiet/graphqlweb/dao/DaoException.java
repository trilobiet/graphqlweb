package com.trilobiet.graphqlweb.dao;

/**
 * 
 * @author acdhirr
 *
 */
public class DaoException extends Exception {

	private static final long serialVersionUID = 1L;

	public DaoException(Exception root) {
		super(root);
	}

	public DaoException(String message) {
		super(message);
	}
	
	public DaoException() {
		super();
	}
	
}
