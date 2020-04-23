package com.trilobiet.graphqlweb.implementations.aexpgraphql2.topic;

import java.util.List;

import io.aexp.nodes.graphql.annotations.GraphQLArgument;
import io.aexp.nodes.graphql.annotations.GraphQLArguments;

/**
 * 
 * @author acdhirr
 *
 */
public class TopicList implements GenericTopicList<TopicImp> {
	
	@GraphQLArguments({
		@GraphQLArgument(
			name = "sort",
			type = "String",
			optional = true, value = "index:asc,name:asc"
		),
		@GraphQLArgument(
			name = "where",
			optional = true, value = "publish:true"
		)
	})
	
	private List<TopicImp> topics;

	public List<TopicImp> getTopics() {
		return topics;
	}

	public void setTopics(List<TopicImp> topics) {
		this.topics = topics;
	}
}
