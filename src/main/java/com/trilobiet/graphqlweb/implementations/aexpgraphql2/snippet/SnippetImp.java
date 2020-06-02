package com.trilobiet.graphqlweb.implementations.aexpgraphql2.snippet;

import java.io.Serializable;

import com.trilobiet.graphqlweb.datamodel.Snippet;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.GraphQLMappable;

import io.aexp.nodes.graphql.annotations.GraphQLArgument;
import io.aexp.nodes.graphql.annotations.GraphQLIgnore;
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
public class SnippetImp extends Snippet implements GraphQLMappable, Serializable {

	@GraphQLIgnore	
	private static final long serialVersionUID = 1L;
}
