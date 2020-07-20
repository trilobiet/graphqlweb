package com.trilobiet.graphqlweb.playground;

import java.net.MalformedURLException;
import java.util.List;

import com.trilobiet.graphqlweb.datamodel.Section;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.article.ArticleImp;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.article.ArticleList;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.section.SectionImp;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.topic.TopicImp;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.topic.TopicList;

import io.aexp.nodes.graphql.Argument;
import io.aexp.nodes.graphql.Arguments;
import io.aexp.nodes.graphql.GraphQLRequestEntity;
import io.aexp.nodes.graphql.GraphQLResponseEntity;
import io.aexp.nodes.graphql.GraphQLTemplate;
import io.aexp.nodes.graphql.InputObject;

class Main {

	public static void main(String[] args) throws IllegalStateException, MalformedURLException {

		GraphQLTemplate graphQLTemplate = new GraphQLTemplate();
		
		//String theserver = "http://localhost:1337/graphql";
		String theserver = "https://oapen-cms.trilobiet.eu/graphql";
		
		System.out.println(graphQLTemplate);

		// 1
		GraphQLRequestEntity requestEntity = GraphQLRequestEntity.Builder()
				.url(theserver)
				.request(ArticleImp.class)
				.arguments(new Arguments("article", new Argument<String>("id", "5e1dce7d2bb811000b64ef4f")))
				.build();
		
		System.out.println(requestEntity);
		GraphQLResponseEntity<ArticleImp> responseEntity = graphQLTemplate.query(requestEntity, ArticleImp.class);
		System.out.println(responseEntity.getResponse());

		
		// 2
/*		InputObject<String> where = new InputObject.Builder<String>()
				  .put("summary_contains", "research")
				  .put("tags_contains", "funder")
				  .build();
	*/	
		
		// category search
		
		InputObject<String> wheresub = new InputObject.Builder<String>()
				.put("name","FUNDERS").build();
				
		
		System.out.println(wheresub);
		
		InputObject<InputObject<String>> where3 = new InputObject.Builder<InputObject<String>>()
				.put("categories", wheresub)
				.build();
		
		System.out.println("where3: " + where3.getMap().toString());
		
		GraphQLRequestEntity requestEntity2 = GraphQLRequestEntity.Builder()
				.url(theserver)
				.request(ArticleList.class)
				.arguments(new Arguments("articles", 
					  new Argument<String>("sort", "title:desc")
					, new Argument<Object>("where", where3)
				))
				.build();
		
		System.out.println("TWEE: " +requestEntity2);
		GraphQLResponseEntity<ArticleList> responseEntity2 = graphQLTemplate.query(requestEntity2, ArticleList.class);
		
		List<ArticleImp> articles = responseEntity2.getResponse().getArticles();
		
		System.out.println("Articles: -----------------------------------------------------------------------------");
		System.out.println(articles);
		
		// Article.Topic p = articles.get(0).getTopics().get(0);
		

		// 4
		GraphQLRequestEntity requestEntity3 = GraphQLRequestEntity.Builder()
				.url(theserver)
				.request(TopicList.class)
				// .arguments(new Arguments("topic", new Argument<String>("id", "5e1dce7d2bb811000b64ef4f")))
				.build();
		
		System.out.println(requestEntity3);
		GraphQLResponseEntity<TopicList> responseEntity3 = graphQLTemplate.query(requestEntity3, TopicList.class);
		
		List<TopicImp> topics = responseEntity3.getResponse().getTopics();
		System.out.println("Topics: -----------------------------------------------------------------------------");
		System.out.println(topics);
		
		
		//5 
		GraphQLRequestEntity requestEntity4 = GraphQLRequestEntity.Builder()
				.url(theserver)
				.request(SectionImp.class)
				.arguments(new Arguments("section", new Argument<String>("id", "5e186f737bf9a003a80f39a2")))
				.build();
		
		System.out.println(requestEntity4);
		GraphQLResponseEntity<SectionImp> responseEntity4 = graphQLTemplate.query(requestEntity4, SectionImp.class);

		Section section = responseEntity4.getResponse();
		System.out.println("Section: -----------------------------------------------------------------------------");
		System.out.println(section);
		
	}

}
