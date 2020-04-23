package com.trilobiet.graphqlweb.implementations.aexpgraphql2.file;

import com.trilobiet.graphqlweb.dao.DaoException;
import com.trilobiet.graphqlweb.dao.FieldValueQuery;
import com.trilobiet.graphqlweb.dao.FieldValueQuery.MatchType;

import io.aexp.nodes.graphql.Argument;
import io.aexp.nodes.graphql.Arguments;
import io.aexp.nodes.graphql.InputObject;

public class FileArgs {

	static Arguments getGetByIdArgs(String id) throws DaoException {
		
		Arguments args = new Arguments("file", new Argument<String>("id", id));
		return args; 
	}
	
	static Arguments getGetByNameArgs(String name) throws DaoException {

		InputObject<String> where = new InputObject.Builder<String>()
			.put("name", name)
			.build();
		
		Arguments args = new Arguments("files", 
			new Argument<String>("sort", "name:asc"),
			new Argument<Object>("where", where) 
		);
		
		return args; 
	}

	static Arguments getFindArgs(FieldValueQuery fv) throws DaoException {
		
		String field = fv.getField();
		if (fv.getMatch() == MatchType.CONTAINS) field = field + "_contains";
		
		InputObject<String> where = new InputObject.Builder<String>()
			.put(field, fv.getValue())
			.build();
		
		Arguments args = new Arguments("files", 
			  new Argument<String>("sort", fv.getSort())
			, new Argument<Object>("where", where) );
		
		return args; 
	}
	
}
