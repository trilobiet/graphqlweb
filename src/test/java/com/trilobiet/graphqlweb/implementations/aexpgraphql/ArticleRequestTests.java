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
public class ArticleRequestTests {
	
	String dummyhost = "http://whatever";

	@Test // getGetByIdRequest
	public void test_graphql_query_for_single_Article_by_Id() 
			throws DaoException {
		
		String id =  "123";
		
		ArticleRequest aq = new ArticleRequest(dummyhost);

		String actualRequest = aq.getGetByIdRequest(id).getRequest().trim();
		String expectedHeadOfRequest = "query { article (id:\"123\") { "; // field names in between
		String expectedTailOfRequest = "} }";
		
		assertTrue( actualRequest.startsWith(expectedHeadOfRequest) );
		assertTrue( actualRequest.endsWith(expectedTailOfRequest) );
	}

	@Test // getListCategoriesRequest
	public void test_graphql_query_for_list_of_Categories() 
			throws DaoException {
		
		ArticleRequest aq = new ArticleRequest(dummyhost);

		String actualRequest = aq.getListCategoriesRequest().getRequest().trim();
		String expectedHeadOfRequest = "query { categories (sort:\"name:asc\") { "; // field names in between
		String expectedTailOfRequest = "} }";
		
		assertTrue( actualRequest.startsWith(expectedHeadOfRequest) );
		assertTrue( actualRequest.endsWith(expectedTailOfRequest) );
	}

	@Test // getListByCategoryRequest
	public void test_graphql_query_for_list_of_Articles_for_Category() 
			throws DaoException {
		
		ArticleRequest aq = new ArticleRequest(dummyhost);
		
		Category cat = new Category();
		cat.setName("TESTCAT");

		String actualRequest = aq.getListByCategoryRequest(cat,"title:desc").getRequest().trim();
		String expectedHeadOfRequest = "query { articles (sort:\"title:desc\",where:{categories:{name:\"TESTCAT\"}}) {"; // field names in between
		String expectedTailOfRequest = "} }";
		
		assertTrue( actualRequest.startsWith(expectedHeadOfRequest) );
		assertTrue( actualRequest.endsWith(expectedTailOfRequest) );
	}
	
	@Test // getListByTopicRequest
	public void test_graphql_query_for_list_of_Articles_for_Topic() 
			throws DaoException {
		
		ArticleRequest aq = new ArticleRequest(dummyhost);
		
		Topic top = new TopicImp();
		top.setName("simsalabim");

		String actualRequest = aq.getListByTopicRequest(top,"title:desc").getRequest().trim();
		String expectedHeadOfRequest = "query { articles (sort:\"title:desc\",where:{topics:{name:\"simsalabim\"}}) {"; // field names in between
		String expectedTailOfRequest = "} }";
		
		assertTrue( actualRequest.startsWith(expectedHeadOfRequest) );
		assertTrue( actualRequest.endsWith(expectedTailOfRequest) );
	}
	
	@Test // getFindRequest
	public void test_graphql_query_for_list_of_Articles_by_searchterm() 
			throws DaoException {
		
		ArticleRequest aq = new ArticleRequest(dummyhost);
		
		String actualRequest = aq.getFindRequest("nonsense", "title:desc").getRequest().trim();
		String expectedHeadOfRequest = "query { articles (sort:\"title:desc\",where:{content_contains:\"nonsense\"}) {"; // field names in between
		String expectedTailOfRequest = "} }";
		
		assertTrue( actualRequest.startsWith(expectedHeadOfRequest) );
		assertTrue( actualRequest.endsWith(expectedTailOfRequest) );
	}
	
}
