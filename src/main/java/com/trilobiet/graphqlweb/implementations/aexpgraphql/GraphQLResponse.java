package com.trilobiet.graphqlweb.implementations.aexpgraphql;

import io.aexp.nodes.graphql.GraphQLRequestEntity;
import io.aexp.nodes.graphql.GraphQLResponseEntity;
import io.aexp.nodes.graphql.GraphQLTemplate;

/**
 * 
 * @author acdhirr
 * @deprecated use ArticleResponse, TopicResponse etc.
 *
 */
class GraphQLResponse<T> {

	final Class<? extends GraphQLMappable> clazz;
	final GraphQLRequestEntity req;

	GraphQLResponse(GraphQLRequestEntity req, Class<? extends GraphQLMappable> clazz) {
		this.clazz = clazz;
		this.req = req;
	}
	
	@SuppressWarnings("unchecked")
	GraphQLResponseEntity<T> getResponseEntity() {
		
		GraphQLTemplate graphQLTemplate = new GraphQLTemplate();
		GraphQLResponseEntity<T> responseEntity = (GraphQLResponseEntity<T>) graphQLTemplate.query(req, clazz);
		return responseEntity;
	}
	
}
