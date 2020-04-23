package com.trilobiet.graphqlweb.implementations.aexpgraphql2.article;

import com.trilobiet.graphqlweb.datamodel.Article;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.GraphQLMappable;

import io.aexp.nodes.graphql.annotations.GraphQLArgument;
import io.aexp.nodes.graphql.annotations.GraphQLProperty;

/**
 * 
 * @author acdhirr
 *
 */
@GraphQLProperty(
	name = "article", 
	arguments = {
		@GraphQLArgument(name = "id")
	}
)
public class ArticleImp extends Article implements GraphQLMappable {
}
