package com.trilobiet.graphqlweb.implementations.aexpgraphql2.snippet;

import java.util.List;

import io.aexp.nodes.graphql.annotations.GraphQLArgument;
import io.aexp.nodes.graphql.annotations.GraphQLArguments;

/**
 * 
 * @author acdhirr
 *
 */
public class SnippetList implements GenericSnippetList<SnippetImp> {
	
	@GraphQLArguments({
		@GraphQLArgument(
			name = "sort",
			type = "String",
			optional = true, value = "name:asc"
		),
		@GraphQLArgument(
			name = "where",
			optional = true
		)
	})
	private List<SnippetImp> snippets;

	public List<SnippetImp> getSnippets() {
		return snippets;
	}

	public void setSnippets(List<SnippetImp> snippets) {
		this.snippets = snippets;
	}
}
