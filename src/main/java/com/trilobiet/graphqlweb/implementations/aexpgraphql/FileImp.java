package com.trilobiet.graphqlweb.implementations.aexpgraphql;

import com.trilobiet.graphqlweb.datamodel.File;

import io.aexp.nodes.graphql.annotations.GraphQLArgument;
import io.aexp.nodes.graphql.annotations.GraphQLProperty;

/**
 * 
 * @author acdhirr
 *
 */
@GraphQLProperty(
	name = "file", 
	arguments = {
		@GraphQLArgument(name = "id", optional=true),
		@GraphQLArgument(name = "where", optional=true)
	}
)
public class FileImp extends File implements GraphQLMappable {
}
