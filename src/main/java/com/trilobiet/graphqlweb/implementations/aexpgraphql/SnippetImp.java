package com.trilobiet.graphqlweb.implementations.aexpgraphql;

import com.trilobiet.graphqlweb.datamodel.Snippet;

import io.aexp.nodes.graphql.annotations.GraphQLArgument;
import io.aexp.nodes.graphql.annotations.GraphQLProperty;

/**
 * 
 * @author acdhirr
 *
 */
@GraphQLProperty(
	name = "snippet", 
	arguments = {
		@GraphQLArgument(name = "id")
	}
)
public class SnippetImp extends Snippet implements GraphQLMappable {
}
