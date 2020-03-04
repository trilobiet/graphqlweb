package com.trilobiet.graphqlweb.implementations.aexpgraphql;

import com.trilobiet.graphqlweb.dao.DaoException;
import com.trilobiet.graphqlweb.datamodel.Topic;

import io.aexp.nodes.graphql.Argument;
import io.aexp.nodes.graphql.Arguments;
import io.aexp.nodes.graphql.GraphQLRequestEntity;
import io.aexp.nodes.graphql.InputObject;

public class SnippetRequest extends GraphQLRequest {

	SnippetRequest(String host) {
		super(host);
	}

	GraphQLRequestEntity getGetByIdRequest(String id) throws DaoException {
		
		Arguments args = new Arguments("snippet", new Argument<String>("id", id));
		return getRequestEntity( args, SnippetImp.class); 
	}
	
	GraphQLRequestEntity getGetByNameRequest(String name) throws DaoException {

		InputObject<String> where = new InputObject.Builder<String>()
				.put("name", name)
				.build();
		
		Arguments args = new Arguments("snippets", 
				new Argument<Object>("where", where) );
		
		return getRequestEntity( args, SnippetList.class); 
	}
	
	GraphQLRequestEntity getListRequest() throws DaoException {

		Arguments args = new Arguments("snippets", 
				  new Argument<String>("sort", "name") );
		
		GraphQLRequestEntity s = getRequestEntity( args, SnippetList.class );
		
		return s; 
	}
	
	
}
