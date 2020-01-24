package com.trilobiet.graphqlweb.implementations.aexpgraphql;

import io.aexp.nodes.graphql.GraphQLRequestEntity;

abstract class GraphQLResponse {

	final GraphQLRequestEntity req;

	GraphQLResponse(GraphQLRequestEntity req) {
		this.req = req;
	}
	
}
