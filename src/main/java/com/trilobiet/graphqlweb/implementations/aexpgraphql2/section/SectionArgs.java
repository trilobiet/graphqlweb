package com.trilobiet.graphqlweb.implementations.aexpgraphql2.section;

import com.trilobiet.graphqlweb.dao.DaoException;

import io.aexp.nodes.graphql.Argument;
import io.aexp.nodes.graphql.Arguments;
import io.aexp.nodes.graphql.InputObject;

public class SectionArgs {
	
	static Arguments getGetByIdArgs(String id) throws DaoException {
		
		Arguments args = new Arguments("section", new Argument<String>("id", id));
		return args; 
	}
	
	static Arguments getListArgs() throws DaoException {
		
		InputObject<String> where = new InputObject.Builder<String>()
			.put("publish", "true")
			.build();

		Arguments args = new Arguments("sections", 
			new Argument<String>("sort", "groupNumber:asc,index:asc,name:asc"),
			new Argument<Object>("where", where) 
		);
		
		return args; 
	}

	static Arguments getBySlugArgs(String slug) throws DaoException {
		
		InputObject<String> where = new InputObject.Builder<String>()
			.put("slug", slug)
			.put("publish", "true")
			.build();
		
		Arguments args = new Arguments("sections", 
			new Argument<String>("sort", "slug:asc"),
			new Argument<Object>("where", where) );
		
		return args; 
	}
	
}
