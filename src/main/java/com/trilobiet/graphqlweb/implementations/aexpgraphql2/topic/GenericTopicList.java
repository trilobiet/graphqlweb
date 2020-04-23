package com.trilobiet.graphqlweb.implementations.aexpgraphql2.topic;

import java.util.List;

import com.trilobiet.graphqlweb.datamodel.Topic;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.GraphQLMappable;

public interface GenericTopicList<T extends Topic> extends GraphQLMappable {

	public List<T> getTopics();
	public void setTopics(List<T> topics);
}

