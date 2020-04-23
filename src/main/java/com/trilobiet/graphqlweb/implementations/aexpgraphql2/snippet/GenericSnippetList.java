package com.trilobiet.graphqlweb.implementations.aexpgraphql2.snippet;

import java.util.List;

import com.trilobiet.graphqlweb.datamodel.Snippet;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.GraphQLMappable;

/**
 * 
 * @author acdhirr
 *
 */
public interface GenericSnippetList<T extends Snippet> extends GraphQLMappable {

	public List<T> getSnippets();
	public void setSnippets(List<T> snippets);
}

