package com.trilobiet.graphqlweb.implementations.aexpgraphql2.article;

import com.trilobiet.graphqlweb.dao.DaoException;
import com.trilobiet.graphqlweb.dao.FieldValueQuery;
import com.trilobiet.graphqlweb.dao.FieldValueQuery.MatchType;
import com.trilobiet.graphqlweb.datamodel.Category;
import com.trilobiet.graphqlweb.datamodel.Topic;

import io.aexp.nodes.graphql.Argument;
import io.aexp.nodes.graphql.Arguments;
import io.aexp.nodes.graphql.InputObject;

/**
 * 
 * @author acdhirr
 *
 */
class ArticleArgs {
	
	static Arguments getListCategoriesArgs() {
		
		Arguments args = new Arguments("categories", 
			new Argument<String>("sort", "name:asc") );

		return args; 
	}
	
	static Arguments getByIdArgs(String id) {
		
		return new Arguments("article", new Argument<String>("id", id));
	}

	static Arguments getGetBySlugArgs(String slug) throws DaoException {

		InputObject<String> where = new InputObject.Builder<String>()
			.put("slug", slug)
			.put("publish", "true")
			.build();
		
		Arguments args = new Arguments("articles", 
			new Argument<String>("sort", "slug:asc"),
			new Argument<Object>("where", where) );
		
		return args; 
	}
	
	static Arguments listByTopicArgs(Topic topic, String sort) {
		
		InputObject<String> nestedWhere = new InputObject.Builder<String>()
				.put("slug",topic.getSlug())
				.build();
					
		InputObject<Object> where = new InputObject.Builder<Object>()
			.put("publish", "true")
			.put("topics", nestedWhere)
			.build();

		Arguments args = new Arguments("articles", 
			  new Argument<String>("sort", sort)
			, new Argument<Object>("where", where) );
		
		return args;
	}
	
	static Arguments getListByCategoryArgs(Category category, String sort) {

		InputObject<String> nestedWhere = new InputObject.Builder<String>()
			.put("name",category.getName())
			.build();
			
		InputObject<Object> where = new InputObject.Builder<Object>()
			.put("publish","true")					
			.put("categories", nestedWhere)
			.build();
		
		Arguments args = new Arguments("articles", 
			  new Argument<String>("sort", sort)
			, new Argument<Object>("where", where) );
		
		return args; 
	}
	
	static Arguments getFindArgs(String searchterm, String sort) {

		// Only searches field 'content'
		// TODO: search title, summary etc. too
		InputObject<String> where = new InputObject.Builder<String>()
			.put("content_contains", searchterm)
			.put("publish", "true")
			.build();

		Arguments args = new Arguments("articles", 
			  new Argument<String>("sort", sort)
			, new Argument<Object>("where", where) );
		
		return args; 
	}

	static Arguments getFindArgs(FieldValueQuery fv) {
		
		String field = fv.getField();
		if (fv.getMatch() == MatchType.CONTAINS) field = field + "_contains";
		
		InputObject<String> where = new InputObject.Builder<String>()
			.put(field, fv.getValue())
			.build();
		
		Arguments args = new Arguments("articles", 
			  new Argument<String>("sort", fv.getSort())
			, new Argument<Object>("where", where) );
		
		return args; 
	}	
	
}
