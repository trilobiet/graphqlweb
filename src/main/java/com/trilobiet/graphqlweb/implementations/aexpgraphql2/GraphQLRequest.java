package com.trilobiet.graphqlweb.implementations.aexpgraphql2;

import java.net.MalformedURLException;

import com.trilobiet.graphqlweb.dao.DaoException;

import io.aexp.nodes.graphql.Arguments;
import io.aexp.nodes.graphql.GraphQLRequestEntity;

/**
 * 
 * @author acdhirr
 *
 */
public class GraphQLRequest {
	
	private final String host;
	
	public GraphQLRequest(String host) {
		this.host = host;
	}

	public final GraphQLRequestEntity getRequestEntity(Arguments args, Class<? extends GraphQLMappable> clazz) throws DaoException{
		
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
