package com.trilobiet.graphqlweb.implementations.aexpgraphql;

import java.util.List;

import com.trilobiet.graphqlweb.datamodel.Section;
import com.trilobiet.graphqlweb.datamodel.Topic;

import io.aexp.nodes.graphql.GraphQLRequestEntity;
import io.aexp.nodes.graphql.GraphQLResponseEntity;
import io.aexp.nodes.graphql.GraphQLTemplate;

/**
 * 
 * @author acdhirr
 *
 */
class TopicResponse {
	
	static Topic getTopic(GraphQLRequestEntity req) {
		
		GraphQLTemplate graphQLTemplate = new GraphQLTemplate();
		GraphQLResponseEntity<TopicImp> responseEntity = graphQLTemplate.query(req, TopicImp.class);
		return responseEntity.getResponse();
	}
	
	static List<Topic> getTopics(GraphQLRequestEntity req) {
		
		GraphQLTemplate graphQLTemplate = new GraphQLTemplate();
		GraphQLResponseEntity<TopicList> responseEntity = graphQLTemplate.query(req, TopicList.class);
		return responseEntity.getResponse().getTopics();
	}

	static List<Section> getSections(GraphQLRequestEntity req) {
		
		GraphQLTemplate graphQLTemplate = new GraphQLTemplate();
		GraphQLResponseEntity<SectionList> responseEntity = graphQLTemplate.query(req, SectionList.class);
		return responseEntity.getResponse().getSections();
	}
	
}
