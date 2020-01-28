package com.trilobiet.graphqlweb.implementations.aexpgraphql;

import java.util.List;

import com.trilobiet.graphqlweb.datamodel.Article;

import io.aexp.nodes.graphql.annotations.GraphQLArgument;
import io.aexp.nodes.graphql.annotations.GraphQLArguments;

/**
 * 
 * @author acdhirr
 *
 */
public class ArticleList implements GraphQLMappable {
	
	@GraphQLArguments({
		@GraphQLArgument(
			name = "sort",
			type = "String",
			optional = true, value = "title:asc"
		),
		@GraphQLArgument(
			name = "where",
			optional = true
		)
	})
	
	private List<Article> articles;

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
}
