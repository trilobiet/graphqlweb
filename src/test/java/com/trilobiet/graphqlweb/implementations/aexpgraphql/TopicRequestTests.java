package com.trilobiet.graphqlweb.implementations.aexpgraphql;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.trilobiet.graphqlweb.dao.DaoException;
import com.trilobiet.graphqlweb.datamodel.Section;

/**
 * Tests for correctness of GraphQL query requests based on model
 * objects. 
 * 
 * @author acdhirr
 *
 */
public class TopicRequestTests {
	
	String dummyhost = "http://whatever";

	@Test // getGetByIdRequest
	public void test_graphql_query_for_single_Topic_by_Id() 
			throws DaoException {
		
		String id =  "123";
		
		TopicRequest tq = new TopicRequest(dummyhost);

		String actualRequest = tq.getGetByIdRequest(id).getRequest().trim();
		String expectedHeadOfRequest = "query { topic (id:\"123\") { "; // field names in between
		String expectedTailOfRequest = "} }";
		
		assertTrue( actualRequest.startsWith(expectedHeadOfRequest) );
		assertTrue( actualRequest.endsWith(expectedTailOfRequest) );
	}

	@Test // getGetBySlugRequest
	public void test_graphql_query_for_single_Topic_by_Slug() 
			throws DaoException {
		
		String slug =  "12345-whatever";
		
		TopicRequest tq = new TopicRequest(dummyhost);

		String actualRequest = tq.getGetBySlugRequest(slug).getRequest().trim();
		String expectedHeadOfRequest = "query { topics (sort:\"slug:asc\",where:{slug:\"12345-whatever\"}) { "; // field names in between
		String expectedTailOfRequest = "} }";

		assertTrue( actualRequest.startsWith(expectedHeadOfRequest) );
		assertTrue( actualRequest.endsWith(expectedTailOfRequest) );
	}
	
	@Test // getListCategoriesRequest
	public void test_graphql_query_for_list_of_Sections() 
			throws DaoException {
		
		TopicRequest tq = new TopicRequest(dummyhost);
		
		String actualRequest = tq.getListSectionsRequest().getRequest().trim();
		// uses default sorting as defined in SectionList.java
		String expectedHeadOfRequest = "query { sections (sort:\"groupNumber:asc,index:asc,name:asc\") { "; // field names in between
		String expectedTailOfRequest = "} }";
		
		assertTrue( actualRequest.startsWith(expectedHeadOfRequest) );
		assertTrue( actualRequest.endsWith(expectedTailOfRequest) );
	}

	@Test // getListByCategoryRequest
	public void test_graphql_query_for_list_of_Topics_for_Section() 
			throws DaoException {
		
		TopicRequest tq = new TopicRequest(dummyhost);
		
		Section sec = new Section();
		sec.setName("TESTSEC");

		String actualRequest = tq.getListBySectionRequest(sec,"title:desc").getRequest().trim();
		String expectedHeadOfRequest = "query { topics (sort:\"title:desc\",where:{sections:{name:\"TESTSEC\"}}) {"; // field names in between
		String expectedTailOfRequest = "} }";
		
		assertTrue( actualRequest.startsWith(expectedHeadOfRequest) );
		assertTrue( actualRequest.endsWith(expectedTailOfRequest) );
	}
	
	@Test // getFindRequest
	public void test_graphql_query_for_list_of_Topics_by_searchname() 
			throws DaoException {
		
		TopicRequest tq = new TopicRequest(dummyhost);
		
		String actualRequest = tq.getFindRequest("nonsense", "title:desc").getRequest().trim();
		String expectedHeadOfRequest = "query { topics (sort:\"title:desc\",where:{name_contains:\"nonsense\"}) {"; // field names in between
		String expectedTailOfRequest = "} }";
		
		assertTrue( actualRequest.startsWith(expectedHeadOfRequest) );
		assertTrue( actualRequest.endsWith(expectedTailOfRequest) );
	}
	
}
