package com.trilobiet.graphqlweb.implementations.aexpgraphql;

import java.util.List;

import com.trilobiet.graphqlweb.datamodel.Snippet;

import io.aexp.nodes.graphql.annotations.GraphQLArgument;
import io.aexp.nodes.graphql.annotations.GraphQLArguments;

/**
 * 
 * @author acdhirr
 *
 */
public class SnippetList implements GraphQLMappable {
	
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
	private List<Snippet> snippets;

	public List<Snippet> getSnippets() {
		return snippets;
	}

	public void setSnippets(List<Snippet> snippets) {
		this.snippets = snippets;
	}
}
