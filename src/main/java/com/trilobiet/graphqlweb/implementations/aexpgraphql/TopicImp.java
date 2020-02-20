package com.trilobiet.graphqlweb.implementations.aexpgraphql;

import com.trilobiet.graphqlweb.datamodel.Topic;

import io.aexp.nodes.graphql.annotations.GraphQLArgument;
import io.aexp.nodes.graphql.annotations.GraphQLProperty;

/**
 * 
 * @author acdhirr
 *
 */
@GraphQLProperty(
	name = "topic", 
	arguments = {
		@GraphQLArgument(name = "id", optional=true)
	}
)
public class TopicImp extends Topic implements GraphQLMappable {
}
