package com.trilobiet.graphqlweb.implementations.aexpgraphql;

import com.trilobiet.graphqlweb.dao.DaoException;
import com.trilobiet.graphqlweb.dao.FieldValueQuery;
import com.trilobiet.graphqlweb.dao.FieldValueQuery.MatchType;
import com.trilobiet.graphqlweb.datamodel.Section;

import io.aexp.nodes.graphql.Argument;
import io.aexp.nodes.graphql.Arguments;
import io.aexp.nodes.graphql.GraphQLRequestEntity;
import io.aexp.nodes.graphql.InputObject;

/**
 * 
 * @author acdhirr
 *
 */
final class TopicRequest extends GraphQLRequest {
	
	TopicRequest(String host) {
		super(host);
	}

	GraphQLRequestEntity getGetByIdRequest(String id) throws DaoException {
		
		Arguments args = new Arguments("topic", new Argument<String>("id", id));
		return getRequestEntity( args, TopicImp.class); 
	}
	
	GraphQLRequestEntity getGetBySlugRequest(String slug) throws DaoException {

		InputObject<String> where = new InputObject.Builder<String>()
			.put("slug", slug)
			.put("publish", "true")
			.build();
		
		Arguments args = new Arguments("topics", 
			new Argument<String>("sort", "slug:asc"),
			new Argument<Object>("where", where) 
		);
		
		return getRequestEntity( args, TopicList.class); 
	}

	GraphQLRequestEntity getListSectionsRequest() throws DaoException {
		
		InputObject<String> where = new InputObject.Builder<String>()
			.put("publish", "true")
			.build();

		Arguments args = new Arguments("sections", 
			new Argument<String>("sort", "groupNumber:asc,index:asc,name:asc"),
			new Argument<Object>("where", where) 
		);
		
		return getRequestEntity( args, SectionList.class ); 
	}

	GraphQLRequestEntity getSectionBySlugRequest(String slug) throws DaoException {
		
		InputObject<String> where = new InputObject.Builder<String>()
			.put("slug", slug)
			.put("publish", "true")
			.build();
		
		Arguments args = new Arguments("sections", 
			new Argument<String>("sort", "slug:asc"),
			new Argument<Object>("where", where) );
		
		return getRequestEntity( args, SectionList.class ); 
	}
	
	GraphQLRequestEntity getListBySectionRequest(Section section, String sort) 
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
		
		GraphQLRequestEntity s = getRequestEntity( args, TopicList.class );
		
		return s; 
	}
	
	GraphQLRequestEntity getFindRequest(String name, String sort) throws DaoException {

		// Only searches field 'name'
		// TODO: search title, summary etc. too
		InputObject<String> where = new InputObject.Builder<String>()
			.put("name_contains", name)
			.put("publish", "true")
			.build();

		Arguments args = new Arguments("topics", 
			  new Argument<String>("sort", sort)
			, new Argument<Object>("where", where) );
		
		return getRequestEntity( args, TopicList.class); 
	}

	public GraphQLRequestEntity getFindRequest(FieldValueQuery fv) throws DaoException {
		
		String field = fv.getField();
		if (fv.getMatch() == MatchType.CONTAINS) field = field + "_contains";
		
		InputObject<String> where = new InputObject.Builder<String>()
			.put(field, fv.getValue())
			.put("publish", "true")
			.build();
		
		Arguments args = new Arguments("topics", 
			  new Argument<String>("sort", fv.getSort())
			, new Argument<Object>("where", where) );
		
		return getRequestEntity( args, TopicList.class); 
	}
	
	
	public static void main(String[] args) throws DaoException {
		
		TopicRequest r = new TopicRequest("https://oapen-cms.trilobiet.eu/graphql");
		Section s = new Section();
		GraphQLRequestEntity q = r.getListBySectionRequest(s,"index:asc");
		System.out.println(q);
	}
	
}
