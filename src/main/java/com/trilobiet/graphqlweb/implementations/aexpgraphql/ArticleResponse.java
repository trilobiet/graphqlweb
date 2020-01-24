package com.trilobiet.graphqlweb.implementations.aexpgraphql;

import java.util.List;

import com.trilobiet.graphqlweb.datamodel.Article;
import com.trilobiet.graphqlweb.datamodel.Category;

import io.aexp.nodes.graphql.GraphQLRequestEntity;
import io.aexp.nodes.graphql.GraphQLResponseEntity;
import io.aexp.nodes.graphql.GraphQLTemplate;

final class ArticleResponse extends GraphQLResponse {
	
	ArticleResponse(GraphQLRequestEntity req) {
		super(req);
	}

	Article getArticle() {
		
		GraphQLTemplate graphQLTemplate = new GraphQLTemplate();
		GraphQLResponseEntity<ArticleImp> responseEntity = graphQLTemplate.query(req, ArticleImp.class);
		return responseEntity.getResponse();
	}
	
	List<Article> getArticles() {
		
		GraphQLTemplate graphQLTemplate = new GraphQLTemplate();
		GraphQLResponseEntity<ArticleList> responseEntity = graphQLTemplate.query(req, ArticleList.class);
		return responseEntity.getResponse().getArticles();
	}

	List<Category> getCategories() {
		
		GraphQLTemplate graphQLTemplate = new GraphQLTemplate();
		GraphQLResponseEntity<CategoryList> responseEntity = graphQLTemplate.query(req, CategoryList.class);
		return responseEntity.getResponse().getCategories();
	}
	
	
}
