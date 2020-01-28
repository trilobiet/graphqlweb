package com.trilobiet.graphqlweb.implementations.aexpgraphql;

import java.util.List;

import com.trilobiet.graphqlweb.datamodel.Article;
import com.trilobiet.graphqlweb.datamodel.Category;

import io.aexp.nodes.graphql.GraphQLRequestEntity;
import io.aexp.nodes.graphql.GraphQLResponseEntity;
import io.aexp.nodes.graphql.GraphQLTemplate;

/**
 * 
 * @author acdhirr
 *
 */
class ArticleResponse {

	static Article getArticle(GraphQLRequestEntity req) {
		
		GraphQLTemplate graphQLTemplate = new GraphQLTemplate();
		GraphQLResponseEntity<ArticleImp> responseEntity = graphQLTemplate.query(req, ArticleImp.class);
		return responseEntity.getResponse();
	}
	
	static List<Article> getArticles(GraphQLRequestEntity req) {
		
		GraphQLTemplate graphQLTemplate = new GraphQLTemplate();
		GraphQLResponseEntity<ArticleList> responseEntity = graphQLTemplate.query(req, ArticleList.class);
		return responseEntity.getResponse().getArticles();
	}

	static List<Category> getCategories(GraphQLRequestEntity req) {
		
		GraphQLTemplate graphQLTemplate = new GraphQLTemplate();
		GraphQLResponseEntity<CategoryList> responseEntity = graphQLTemplate.query(req, CategoryList.class);
		return responseEntity.getResponse().getCategories();
	}
	
}
