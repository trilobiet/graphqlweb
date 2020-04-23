package com.trilobiet.graphqlweb.implementations.aexpgraphql2.article;

import java.util.List;

import io.aexp.nodes.graphql.annotations.GraphQLArgument;
import io.aexp.nodes.graphql.annotations.GraphQLArguments;

/**
 * 
 * @author acdhirr
 *
 */
public class ArticleList implements GenericArticleList<ArticleImp> {
	
	@GraphQLArguments({
		@GraphQLArgument(
			name = "sort",
			type = "String",
			optional = true, value = "index:asc,slug:asc"
		),
		@GraphQLArgument(
			name = "where",
			optional = false, value = "publish:true"
		)
	})
	
	private List<ArticleImp> articles;

	public List<ArticleImp> getArticles() {
		return articles;
	}

	public void setArticles(List<ArticleImp> articles) {
		this.articles = articles;
	}
}
