package com.trilobiet.graphqlweb.implementations.aexpgraphql;

import com.trilobiet.graphqlweb.datamodel.Category;
import com.trilobiet.graphqlweb.datamodel.DaoException;
import com.trilobiet.graphqlweb.datamodel.Topic;

import io.aexp.nodes.graphql.Argument;
import io.aexp.nodes.graphql.Arguments;
import io.aexp.nodes.graphql.GraphQLRequestEntity;
import io.aexp.nodes.graphql.InputObject;

final class ArticleRequest extends GraphQLRequest {
	
	ArticleRequest(String host) {
		super(host);
	}

	GraphQLRequestEntity getGetByIdRequest(String id) throws DaoException {
		
		Arguments args = new Arguments("article", new Argument<String>("id", id));
		return getRequestEntity( args, ArticleImp.class); 
	}
	
	GraphQLRequestEntity getListCategoriesRequest()	throws DaoException {
		
		Arguments args = new Arguments("categories", 
				  new Argument<String>("sort", "name:asc") );
		return getRequestEntity( args, CategoryList.class ); 
	}
	
	GraphQLRequestEntity getListByCategoryRequest(Category category, String sort) 
			throws DaoException {

		InputObject<String> deepFilter = new InputObject.Builder<String>()
			.put("name",category.getName()).build();
			
		InputObject<InputObject<String>> where = new InputObject.Builder<InputObject<String>>()
				.put("categories", deepFilter)
				.build();
		
		Arguments args = new Arguments("articles", 
				  new Argument<String>("sort", sort)
				, new Argument<Object>("where", where) );
		
		GraphQLRequestEntity s = getRequestEntity( args, ArticleList.class );
		
		return s; 
	}
	
	GraphQLRequestEntity getListByTopicRequest(Topic topic, String sort) throws DaoException {

		InputObject<String> deepFilter = new InputObject.Builder<String>()
				.put("name",topic.getName()).build();
				
		InputObject<InputObject<String>> where = new InputObject.Builder<InputObject<String>>()
				.put("topics", deepFilter)
				.build();

		Arguments args = new Arguments("articles", 
				  new Argument<String>("sort", sort)
				, new Argument<Object>("where", where) );
		
		GraphQLRequestEntity s = getRequestEntity( args, ArticleList.class );
		
		return s; 
	}
	
	GraphQLRequestEntity getFindRequest(String searchterm, String sort)	throws DaoException {

		// Only searches field 'content'
		// TODO: search title, summary etc. too
		InputObject<String> where = new InputObject.Builder<String>()
				.put("content_contains", searchterm)
				.build();

		Arguments args = new Arguments("articles", 
				  new Argument<String>("sort", sort)
				, new Argument<Object>("where", where) );
		
		return getRequestEntity( args, ArticleList.class); 
	}
	
}
