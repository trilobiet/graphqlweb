package com.trilobiet.graphqlweb.playground;

import com.trilobiet.graphqlweb.implementations.aexpgraphql2.article.ArticleImp;

import io.aexp.nodes.graphql.annotations.GraphQLArgument;
import io.aexp.nodes.graphql.annotations.GraphQLProperty;

@GraphQLProperty(
		name = "article", 
		arguments = {
			@GraphQLArgument(name = "id")
		}
	)
public class TKArticle extends ArticleImp {

	/*
	 * Only when references are truly in the db
	public String references;

	public void setReferences(String references) {
		this.references = references;
	}
	*/

	public String getReferences() {
		return "your references";
	}

	@Override
	public String toString() {
		return "TKArticle [references=" + getReferences() + ", toString()=" + super.toString() + "]";
	}

}
