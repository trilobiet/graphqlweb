package com.trilobiet.graphqlweb.implementations.aexpgraphql;

import java.util.List;

import com.trilobiet.graphqlweb.datamodel.Snippet;

import io.aexp.nodes.graphql.GraphQLRequestEntity;
import io.aexp.nodes.graphql.GraphQLResponseEntity;
import io.aexp.nodes.graphql.GraphQLTemplate;

/**
 * 
 * @author acdhirr
 *
 */
class SnippetResponse {
	
	static Snippet getSnippet(GraphQLRequestEntity req) {
		
		GraphQLTemplate graphQLTemplate = new GraphQLTemplate();
		GraphQLResponseEntity<SnippetImp> responseEntity = graphQLTemplate.query(req, SnippetImp.class);
		return responseEntity.getResponse();
	}
	
	static List<Snippet> getSnippets(GraphQLRequestEntity req) {
		
		GraphQLTemplate graphQLTemplate = new GraphQLTemplate();
		GraphQLResponseEntity<SnippetList> responseEntity = graphQLTemplate.query(req, SnippetList.class);
		return responseEntity.getResponse().getSnippets();
	}

}
