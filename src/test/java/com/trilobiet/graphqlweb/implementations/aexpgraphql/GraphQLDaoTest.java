package com.trilobiet.graphqlweb.implementations.aexpgraphql;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.trilobiet.graphqlweb.dao.ArticleDao;
import com.trilobiet.graphqlweb.dao.DaoException;
import com.trilobiet.graphqlweb.dao.TopicDao;
import com.trilobiet.graphqlweb.datamodel.Category;
import com.trilobiet.graphqlweb.datamodel.Section;
import com.trilobiet.graphqlweb.datamodel.Topic;

import io.aexp.nodes.graphql.GraphQLRequestEntity;
import io.aexp.nodes.graphql.GraphQLResponseEntity;
import io.aexp.nodes.graphql.GraphQLTemplate;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

public class GraphQLDaoTest {
	
    protected MockWebServer mockserver;
    protected Path resourceDirectory;
    protected String host;

    @Before
    public void setupMockServer() throws Exception {
        mockserver = new MockWebServer();
        mockserver.start();
        resourceDirectory = Paths.get("src","test","resources");
        host = "http://" + mockserver.getHostName() + ":" + mockserver.getPort() + "/graphql";
    }

    @After
    public void tearDownMockServer() throws Exception {
       mockserver.shutdown();
    }	
    
    
    @Test
	public void list_categories_test() throws IOException, DaoException {
    	
    	ArticleDao dao = new GraphQLArticleDao( host );
    	enqueueResponseFromResourceFile("categories-test-1.json");
		List<Category> categories = dao.listCategories();
		
		assertThat(categories, containsInAnyOrder(
				hasProperty("name", is("CATEGORY_3")),
                hasProperty("name", is("CATEGORY_1")),
                hasProperty("name", is("CATEGORY_2")),
                hasProperty("name", is("CATEGORY_4"))
        ));	
	}

    
    @Test
	public void list_topics_test() throws IOException, DaoException {
    	
    	TopicDao dao = new GraphQLTopicDao( host );
    	enqueueResponseFromResourceFile("topics-test-1.json");
    	Section mocksec = new Section();
		List<Topic> topics = dao.list(mocksec, "");
		
		assertThat(topics, containsInAnyOrder(
                hasProperty("name", is("Topic 1")),
                hasProperty("name", is("Topic 2")),
                hasProperty("name", is("Topic 3"))
        ));	
		
        // Random deep test
        assertEquals( topics.get(2).getArticles().get(0).getSlug(), "/article3" );
	}

    @Test
	public void get_topic_test() throws IOException, DaoException {
    	
    	TopicDao dao = new GraphQLTopicDao( host );
		enqueueResponseFromResourceFile("topic-test-1.json");
		Topic topic = dao.get("mockid");
		
		assertThat(topic, hasProperty("name", is("THE TOPIC")));
		assertThat(topic, hasProperty("articles", Matchers.isA(List.class) ) );
		assertThat(topic, hasProperty("articles", Matchers.hasSize(2) ) );
		
        // Random deep test
        //assertEquals( topics.get(2).getArticles().get(0).getSlug(), "/article3" );
	}
    
    @Test
	public void list_sections_test() throws IOException, DaoException {
    	
    	TopicDao dao = new GraphQLTopicDao( host );
    	enqueueResponseFromResourceFile("sections-test-1.json");
		List<Section> sections = dao.listSections();
		
		//Test class property, and its value
        assertThat(sections, containsInAnyOrder(
                hasProperty("name", is("SECTION 1")),
                hasProperty("name", is("SECTION 2")),
                hasProperty("name", is("SECTION 3"))
        ));		

        // Random deep test
		assertEquals( sections.get(2).getTranslations().get(0).getLanguageCode(), "NL" );
		
	}
    
    
    protected void enqueueResponseFromResourceFile(String filename) throws IOException {
    	
		Path testfile = resourceDirectory.resolve(filename);
		String json = Files.readString( testfile );
		enqueueResponse(json);
    }
    
    protected void enqueueResponse(String json) {
    	
		MockResponse response = new MockResponse()
			    .addHeader("Content-Type", "application/json; charset=utf-8")
			    .addHeader("Cache-Control", "no-cache")
			    .setResponseCode(200)
			    .setBody( json );		
		
		mockserver.enqueue(response);
    }
    
    protected GraphQLResponseEntity<? extends GraphQLMappable> mockServerResponse(
    		String json, String mockUrl, Class<? extends GraphQLMappable> clazz) 
    				throws IOException {
    	
		MockResponse response = new MockResponse()
			    .addHeader("Content-Type", "application/json; charset=utf-8")
			    .addHeader("Cache-Control", "no-cache")
			    .setResponseCode(200)
			    .setBody( json );		
		
		mockserver.enqueue(response);
		
		HttpUrl mockServiceUrl = mockserver.url("/testCategoriesMapping");
		
        GraphQLRequestEntity requestEntity = GraphQLRequestEntity.Builder()
                .url(mockServiceUrl.toString())
                .request(clazz)
                .build();		

        GraphQLTemplate graphQLTemplate = new GraphQLTemplate();
        GraphQLResponseEntity<? extends GraphQLMappable> resp = graphQLTemplate.mutate(requestEntity, clazz);
        
        return resp;
    }
	
	
}
