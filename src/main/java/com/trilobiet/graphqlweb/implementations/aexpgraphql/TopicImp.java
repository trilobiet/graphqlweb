package com.trilobiet.graphqlweb.implementations.aexpgraphql;

import com.trilobiet.graphqlweb.datamodel.Topic;

import io.aexp.nodes.graphql.annotations.GraphQLArgument;
import io.aexp.nodes.graphql.annotations.GraphQLProperty;

@GraphQLProperty(name="topic", arguments = {@GraphQLArgument(name = "id")})
public class TopicImp extends Topic implements GraphQLMappable {

	
}
