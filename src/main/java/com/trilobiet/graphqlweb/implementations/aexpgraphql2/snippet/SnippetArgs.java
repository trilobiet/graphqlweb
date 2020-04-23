package com.trilobiet.graphqlweb.implementations.aexpgraphql2.snippet;

import com.trilobiet.graphqlweb.dao.DaoException;

import io.aexp.nodes.graphql.Argument;
import io.aexp.nodes.graphql.Arguments;
import io.aexp.nodes.graphql.InputObject;

public class SnippetArgs {
	
	static Arguments getGetByIdArgs(String id) throws DaoException {
		
		Arguments args = new Arguments("snippet", new Argument<String>("id", id));
		return args; 
	}
	
	static Arguments getGetByNameArgs(String name) throws DaoException {

		InputObject<String> where = new InputObject.Builder<String>()
				.put("name", name)
				.build();
		
		Arguments args = new Arguments("snippets", 
				new Argument<Object>("where", where) );
		
		return args; 
	}
	
	static Arguments getListArgs() throws DaoException {

		Arguments args = new Arguments("snippets", 
				  new Argument<String>("sort", "name") );
		
		return args; 
	}

}
