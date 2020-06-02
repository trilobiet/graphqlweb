package com.trilobiet.graphqlweb.implementations.aexpgraphql2.topic;

import java.io.Serializable;

import com.trilobiet.graphqlweb.datamodel.Topic;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.GraphQLMappable;

import io.aexp.nodes.graphql.annotations.GraphQLArgument;
import io.aexp.nodes.graphql.annotations.GraphQLIgnore;
import io.aexp.nodes.graphql.annotations.GraphQLProperty;

/**
 * 
 * @author acdhirr
 *
 */
@GraphQLProperty(
	name = "topic", 
	arguments = {
		@GraphQLArgument(name = "id", optional=true),
		@GraphQLArgument(name = "where", optional=true)
	}
)
public class TopicImp extends Topic implements GraphQLMappable, Serializable {

	@GraphQLIgnore
	private static final long serialVersionUID = 1L;
}
