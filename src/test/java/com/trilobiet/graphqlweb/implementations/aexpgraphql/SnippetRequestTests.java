package com.trilobiet.graphqlweb.implementations.aexpgraphql;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.trilobiet.graphqlweb.dao.DaoException;
import com.trilobiet.graphqlweb.datamodel.Category;
import com.trilobiet.graphqlweb.datamodel.Topic;

/**
 * Tests for correctness of GraphQL query requests based on model
 * objects. 
 * 
 * @author acdhirr
 *
 */
public class SnippetRequestTests {
	
	String dummyhost = "http://whatever";

	@Test // getGetByIdRequest
	public void test_graphql_query_for_single_Snippet_by_Id() 
			throws DaoException {
		
		String id =  "123";
		
		SnippetRequest sq = new SnippetRequest(dummyhost);

		String actualRequest = sq.getGetByIdRequest(id).getRequest().trim();
		String expectedHeadOfRequest = "query { snippet (id:\"123\") { "; // field names in between
		String expectedTailOfRequest = "} }";
		
		assertTrue( actualRequest.startsWith(expectedHeadOfRequest) );
		assertTrue( actualRequest.endsWith(expectedTailOfRequest) );
	}

	@Test // getGetByNameRequest
	public void test_graphql_query_for_single_Snippet_by_Name() 
			throws DaoException {
		
		String name = "whatever";
		
		SnippetRequest sq = new SnippetRequest(dummyhost);

		String actualRequest = sq.getGetByNameRequest(name).getRequest().trim();
		String expectedHeadOfRequest = "query { snippets (sort:\"name:asc\",where:{name:\"whatever\"}) {"; // field names in between
		String expectedTailOfRequest = "} }";

		System.out.println(actualRequest);
		assertTrue( actualRequest.startsWith(expectedHeadOfRequest) );
		assertTrue( actualRequest.endsWith(expectedTailOfRequest) );
	}
	
	@Test // getListSnippetsRequest
	public void test_graphql_query_for_list_of_Categories() 
			throws DaoException {
		
		SnippetRequest sq = new SnippetRequest(dummyhost);

		String actualRequest = sq.getListRequest().getRequest().trim();
		String expectedHeadOfRequest = "query { snippets (sort:\"name\") {"; // field names in between
		String expectedTailOfRequest = "} }";
		
		assertTrue( actualRequest.startsWith(expectedHeadOfRequest) );
		assertTrue( actualRequest.endsWith(expectedTailOfRequest) );
	}	

}
