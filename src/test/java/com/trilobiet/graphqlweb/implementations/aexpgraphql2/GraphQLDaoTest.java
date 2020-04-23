package com.trilobiet.graphqlweb.implementations.aexpgraphql2;

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

import com.trilobiet.graphqlweb.dao.DaoException;
import com.trilobiet.graphqlweb.datamodel.Category;
import com.trilobiet.graphqlweb.datamodel.File;
import com.trilobiet.graphqlweb.datamodel.Section;
import com.trilobiet.graphqlweb.datamodel.Snippet;
import com.trilobiet.graphqlweb.datamodel.Topic;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.article.ArticleImp;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.article.ArticleList;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.article.GenericArticleDao;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.article.GenericArticleList;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.file.FileImp;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.file.FileList;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.file.GenericFileDao;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.file.GenericFileList;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.snippet.GenericSnippetDao;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.snippet.GenericSnippetList;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.snippet.SnippetImp;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.snippet.SnippetList;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.topic.GenericTopicDao;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.topic.GenericTopicList;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.topic.TopicImp;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.topic.TopicList;

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
    	
    	GenericArticleDao<ArticleImp, GenericArticleList<ArticleImp>> dao 
			= new GenericArticleDao<>(host, ArticleImp.class, ArticleList.class);
    	
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
    	
    	GenericTopicDao<TopicImp, GenericTopicList<TopicImp>> dao 
			= new GenericTopicDao<>(host, TopicImp.class, TopicList.class);

    	enqueueResponseFromResourceFile("topics-test-1.json");
    	Section mocksec = new Section();
		List<? extends Topic> topics = dao.list(mocksec, "");
		
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
    	
    	GenericTopicDao<TopicImp, GenericTopicList<TopicImp>> dao 
		= new GenericTopicDao<>(host, TopicImp.class, TopicList.class);
    	
		enqueueResponseFromResourceFile("topic-test-1.json");
		Topic topic = dao.get("mockid").get(); // Optional!
		
		assertThat(topic, hasProperty("name", is("THE TOPIC")));
		assertThat(topic, hasProperty("articles", Matchers.isA(List.class) ) );
		assertThat(topic, hasProperty("articles", Matchers.hasSize(2) ) );
		
        // Random deep test
        //assertEquals( topics.get(2).getArticles().get(0).getSlug(), "/article3" );
	}

    @Test
	public void list_snippets_test() throws IOException, DaoException {
    	
    	GenericSnippetDao<SnippetImp, GenericSnippetList<SnippetImp>> dao 
			= new GenericSnippetDao<>(host, SnippetImp.class, SnippetList.class);
    	
    	enqueueResponseFromResourceFile("snippets-test-1.json");
		List<? extends Snippet> snippets = dao.list();
		
		assertThat(snippets, containsInAnyOrder(
                hasProperty("name", is("a_snippet")),
                hasProperty("name", is("another_snippet")),
                hasProperty("name", is("yet_another_snippet"))
        ));	
	}
    
    @Test
	public void get_snippet_test() throws IOException, DaoException {
    	
    	GenericSnippetDao<SnippetImp, GenericSnippetList<SnippetImp>> dao 
			= new GenericSnippetDao<>(host, SnippetImp.class, SnippetList.class);

    	enqueueResponseFromResourceFile("snippet-test-1.json");
		Snippet snip = dao.get("mockid").get(); // Optional!
		
		assertThat(snip, hasProperty("name", is("a_snippet")));
		assertThat(snip, hasProperty("code"));
	}
    

    @Test
	public void get_file_test() throws IOException, DaoException {
    	
    	GenericFileDao<FileImp, GenericFileList<FileImp>> dao 
			= new GenericFileDao<>(host, FileImp.class, FileList.class);

		enqueueResponseFromResourceFile("file-test-1.json");
		File file = dao.get("mockid").get(); // Optional!
		
		assertThat(file, hasProperty("name", is("file.png")));
		assertThat(file, hasProperty("mime"));
	}
    
    
    
    @Test
	public void list_sections_test() throws IOException, DaoException {
    	
    	GenericTopicDao<TopicImp, GenericTopicList<TopicImp>> dao 
    		= new GenericTopicDao<>(host, TopicImp.class, TopicList.class);
    	
    	enqueueResponseFromResourceFile("sections-test-1.json");
		List<? extends Section> sections = dao.listSections();
		
		//Test class property, and its value
        assertThat(sections, containsInAnyOrder(
                hasProperty("name", is("SECTION 1")),
                hasProperty("name", is("SECTION 2")),
                hasProperty("name", is("SECTION 3"))
        ));		

        // Random deep test
		assertEquals( sections.get(2).getTranslations().get(0).getLanguageCode(), "NL" );
	}

    @Test
	public void get_section_test() throws IOException, DaoException {
    	
    	GenericTopicDao<TopicImp, GenericTopicList<TopicImp>> dao 
    		= new GenericTopicDao<>(host, TopicImp.class, TopicList.class);

		enqueueResponseFromResourceFile("sections-test-1.json");
		Section section = dao.getSectionBySlug("lalala").get(); // Optional!
		
		assertThat(section, hasProperty("name", is("SECTION 1")));
		assertThat(section, hasProperty("topics", Matchers.isA(List.class) ) );
		assertThat(section, hasProperty("topics", Matchers.hasSize(1) ) );
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
