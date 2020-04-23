package com.trilobiet.graphqlweb.implementations.aexpgraphql2.topic;

import com.trilobiet.graphqlweb.dao.DaoException;
import com.trilobiet.graphqlweb.dao.FieldValueQuery;
import com.trilobiet.graphqlweb.dao.FieldValueQuery.MatchType;
import com.trilobiet.graphqlweb.datamodel.Section;

import io.aexp.nodes.graphql.Argument;
import io.aexp.nodes.graphql.Arguments;
import io.aexp.nodes.graphql.InputObject;

public class TopicArgs {
	
	static Arguments getGetByIdArgs(String id) throws DaoException {
		
		Arguments args = new Arguments("topic", new Argument<String>("id", id));
		return args; 
	}
	
	static Arguments getGetBySlugArgs(String slug) throws DaoException {

		InputObject<String> where = new InputObject.Builder<String>()
			.put("slug", slug)
			.put("publish", "true")
			.build();
		
		Arguments args = new Arguments("topics", 
			new Argument<String>("sort", "slug:asc"),
			new Argument<Object>("where", where) 
		);
		
		return args; 
	}

	static Arguments getListSectionsArgs() throws DaoException {
		
		InputObject<String> where = new InputObject.Builder<String>()
			.put("publish", "true")
			.build();

		Arguments args = new Arguments("sections", 
			new Argument<String>("sort", "groupNumber:asc,index:asc,name:asc"),
			new Argument<Object>("where", where) 
		);
		
		return args; 
	}

	static Arguments getSectionBySlugArgs(String slug) throws DaoException {
		
		InputObject<String> where = new InputObject.Builder<String>()
			.put("slug", slug)
			.put("publish", "true")
			.build();
		
		Arguments args = new Arguments("sections", 
			new Argument<String>("sort", "slug:asc"),
			new Argument<Object>("where", where) );
		
		return args; 
	}
	
	static Arguments getListBySectionArgs(Section section, String sort) 
			throws DaoException {

		InputObject<String> nestedWhere = new InputObject.Builder<String>()
			.put("name",section.getName())
			.build();
		
		InputObject<Object> where = new InputObject.Builder<Object>()
			.put("publish", "true")
			.put("sections", nestedWhere)
			.build();
		
		Arguments args = new Arguments("topics", 
			  new Argument<String>("sort", sort)
			, new Argument<Object>("where", where) );
		
		return args; 
	}
	
	static Arguments getFindArgs(String name, String sort) throws DaoException {

		// Only searches field 'name'
		// TODO: search title, summary etc. too
		InputObject<String> where = new InputObject.Builder<String>()
			.put("name_contains", name)
			.put("publish", "true")
			.build();

		Arguments args = new Arguments("topics", 
			  new Argument<String>("sort", sort)
			, new Argument<Object>("where", where) );
		
		return args; 
	}

	static Arguments getFindArgs(FieldValueQuery fv) throws DaoException {
		
		String field = fv.getField();
		if (fv.getMatch() == MatchType.CONTAINS) field = field + "_contains";
		
		InputObject<String> where = new InputObject.Builder<String>()
			.put(field, fv.getValue())
			.put("publish", "true")
			.build();
		
		Arguments args = new Arguments("topics", 
			  new Argument<String>("sort", fv.getSort())
			, new Argument<Object>("where", where) );
		
		return args; 
	}	
	

}
