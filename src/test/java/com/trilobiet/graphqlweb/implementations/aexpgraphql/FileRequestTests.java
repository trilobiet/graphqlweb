package com.trilobiet.graphqlweb.implementations.aexpgraphql;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.trilobiet.graphqlweb.dao.DaoException;

/**
 * Tests for correctness of GraphQL query requests based on model
 * objects. 
 * 
 * NOTE: GraphQL is NOT Json - known testers here are not useable...
 * TODO find some tester for GraphQL
 * 
 * @author acdhirr
 *
 */
public class FileRequestTests {
	
	String dummyhost = "http://whatever";

	@Test // getGetByIdRequest
	public void test_graphql_query_for_single_File_by_Id() 
			throws DaoException {
		
		String id =  "123";
		
		FileRequest sq = new FileRequest(dummyhost);

		String actualRequest = sq.getGetByIdRequest(id).getRequest().trim();
		String expectedHeadOfRequest = "query { file (id:\"123\") { "; // field names in between
		String expectedTailOfRequest = "} }";
		
		assertTrue( actualRequest.startsWith(expectedHeadOfRequest) );
		assertTrue( actualRequest.endsWith(expectedTailOfRequest) );
	}

	@Test // getGetByNameRequest
	public void test_graphql_query_for_single_File_by_Name() 
			throws DaoException {
		
		String name = "whatever";
		
		FileRequest sq = new FileRequest(dummyhost);

		String actualRequest = sq.getGetByNameRequest(name).getRequest().trim();
		String expectedHeadOfRequest = "query { files (sort:\"name:asc\",where:{name:\"whatever\"}) {"; // field names in between
		String expectedTailOfRequest = "} }";
		
		assertTrue( actualRequest.startsWith(expectedHeadOfRequest) );
		assertTrue( actualRequest.endsWith(expectedTailOfRequest) );
	}
	
}
