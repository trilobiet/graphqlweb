package com.trilobiet.graphqlweb.dao;

public interface FieldValueQuery {
	
	String getField();
	String getValue();
	String getSort();
	MatchType getMatch();	
	
	static enum MatchType {
		EQUALS, CONTAINS;
	}

}
