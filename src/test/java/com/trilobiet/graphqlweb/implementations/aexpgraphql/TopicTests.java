package com.trilobiet.graphqlweb.implementations.aexpgraphql;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.trilobiet.graphqlweb.datamodel.Topic;
import com.trilobiet.graphqlweb.implementations.aexpgraphql.TopicImp;
import com.trilobiet.graphqlweb.implementations.aexpgraphql.TopicList;

import io.aexp.nodes.graphql.Argument;
import io.aexp.nodes.graphql.Arguments;
import io.aexp.nodes.graphql.GraphQLRequestEntity;
import io.aexp.nodes.graphql.GraphQLResponseEntity;
import io.aexp.nodes.graphql.GraphQLTemplate;
import io.aexp.nodes.graphql.InputObject;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

public class TopicTests {

    // @Rule public MockitoRule mockitoRule = MockitoJUnit.rule(); 		
	
    private MockWebServer testserver;
    private Path resourceDirectory;

    @Before
    public void setupMockServer() throws Exception {
        testserver = new MockWebServer();
        testserver.start();
        resourceDirectory = Paths.get("src","test","resources");
    }

    @After
    public void tearDownMockServer() throws Exception {
        testserver.shutdown();
    }

	
	@Test
	public void testNothing() {
		assertEquals("These are not the same, ", 123, 123);
	}
	
	@Test
	public void test_json_query_for_single_Topic_by_Id() throws IllegalStateException, MalformedURLException {
		
		String id =  "123";
		
		GraphQLRequestEntity req = GraphQLRequestEntity.Builder()
				.url("http://whatever")
				.request(TopicImp.class)
				.arguments(new Arguments("topic", new Argument<String>("id", id)))
				.build();
		
		String resultQuery = req.getRequest().trim();
		String expectedQuery = "query { topic (id:\"" + id + "\") { name id articles { id title slug } } }";
		
		assertEquals( expectedQuery, resultQuery );
	}
	

	@Test
	public void test_json_query_for_list_of_Topics_by_filtering() throws IllegalStateException, MalformedURLException {
		
		InputObject<String> where = new InputObject.Builder<String>()
				  .put("name_contains", "abc")
				  .build();		
		
		GraphQLRequestEntity req = GraphQLRequestEntity.Builder()
				.url("http://whatever")
				.request(TopicList.class)
				.arguments(new Arguments("topics", 
						  new Argument<String>("sort", "name:desc")
						, new Argument<Object>("where", where)
					))
				.build();		
		
		String resultQuery = req.getRequest().trim();
		String expectedQuery = "query { topics (sort:\"name:desc\",where:{name_contains:\"abc\"}) { name id articles { id title slug } } }";
		
		
		assertEquals( expectedQuery, resultQuery );
	}
	
	
	@Test
	public void test_creation_of_Topic_objects_from_json() throws IOException {
		
		// see src/text/resources
		Path testfile = resourceDirectory.resolve("topics-test-1.json");
		String json = Files.readString( testfile );
		
		MockResponse response = new MockResponse()
			    .addHeader("Content-Type", "application/json; charset=utf-8")
			    .addHeader("Cache-Control", "no-cache")
			    .setResponseCode(200)
			    .setBody( json );		
		
		testserver.enqueue(response);
		
		HttpUrl serviceUrl = testserver.url("/testTopicsMapping");
		
        GraphQLRequestEntity requestEntity = GraphQLRequestEntity.Builder()
                .url(serviceUrl.toString())
                .request(TopicList.class)
                .build();		
		
        GraphQLTemplate graphQLTemplate = new GraphQLTemplate();
        GraphQLResponseEntity<TopicList> topiclist = graphQLTemplate.mutate(requestEntity, TopicList.class);
        
        List<Topic> topics = topiclist.getResponse().getTopics();
        
        assertEquals( topics.size(), 3 );
        assertEquals( topics.get(0).getName(), "Topic 1" );
        assertEquals( topics.get(1).getArticles().size(), 1 );
        assertEquals( topics.get(2).getArticles().get(0).getSlug(), "/article3" );
		
	}
	
}
