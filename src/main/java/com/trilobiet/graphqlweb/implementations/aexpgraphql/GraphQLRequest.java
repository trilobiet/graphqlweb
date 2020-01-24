package com.trilobiet.graphqlweb.implementations.aexpgraphql;

import java.net.MalformedURLException;

import com.trilobiet.graphqlweb.datamodel.DaoException;

import io.aexp.nodes.graphql.Arguments;
import io.aexp.nodes.graphql.GraphQLRequestEntity;

abstract class GraphQLRequest {
	
	private final String host;
	
	GraphQLRequest(String host) {
		this.host = host;
	}

	final GraphQLRequestEntity getRequestEntity(Arguments args, Class<? extends GraphQLMappable> clazz) throws DaoException{
		
		try {
			GraphQLRequestEntity requestEntity = GraphQLRequestEntity.Builder()
					.url( this.host )
					.request( clazz )
					.arguments( args )
					.build();
			
			return requestEntity;

		} catch (IllegalStateException | MalformedURLException e) {
			
			throw new DaoException(e);
		}
	}
}
