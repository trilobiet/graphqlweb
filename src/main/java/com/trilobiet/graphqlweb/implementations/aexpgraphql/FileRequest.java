package com.trilobiet.graphqlweb.implementations.aexpgraphql;

import com.trilobiet.graphqlweb.dao.DaoException;
import com.trilobiet.graphqlweb.dao.FieldValueQuery;
import com.trilobiet.graphqlweb.dao.FieldValueQuery.MatchType;

import io.aexp.nodes.graphql.Argument;
import io.aexp.nodes.graphql.Arguments;
import io.aexp.nodes.graphql.GraphQLRequestEntity;
import io.aexp.nodes.graphql.InputObject;

/**
 * 
 * @author acdhirr
 *
 */
final class FileRequest extends GraphQLRequest {
	
	FileRequest(String host) {
		super(host);
	}

	GraphQLRequestEntity getGetByIdRequest(String id) throws DaoException {
		
		Arguments args = new Arguments("file", new Argument<String>("id", id));
		return getRequestEntity( args, FileImp.class); 
	}
	
	GraphQLRequestEntity getGetByNameRequest(String name) throws DaoException {

		InputObject<String> where = new InputObject.Builder<String>()
			.put("name", name)
			.build();
		
		Arguments args = new Arguments("files", 
			new Argument<String>("sort", "name:asc"),
			new Argument<Object>("where", where) 
		);
		
		return getRequestEntity( args, FileList.class); 
	}

	public GraphQLRequestEntity getFindRequest(FieldValueQuery fv) throws DaoException {
		
		String field = fv.getField();
		if (fv.getMatch() == MatchType.CONTAINS) field = field + "_contains";
		
		InputObject<String> where = new InputObject.Builder<String>()
			.put(field, fv.getValue())
			.build();
		
		Arguments args = new Arguments("files", 
			  new Argument<String>("sort", fv.getSort())
			, new Argument<Object>("where", where) );
		
		return getRequestEntity( args, TopicList.class); 
	}
	
	
	public static void main(String[] args) throws DaoException {
		
		FileRequest r = new FileRequest("https://oapen-cms.trilobiet.eu/graphql");
		GraphQLRequestEntity q = r.getGetByNameRequest("hallo.jpg");
		System.out.println(q);
	}
	
}
