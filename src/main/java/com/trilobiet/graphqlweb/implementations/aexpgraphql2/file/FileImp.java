package com.trilobiet.graphqlweb.implementations.aexpgraphql2.file;

import java.io.Serializable;

import com.trilobiet.graphqlweb.datamodel.File;
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
	name = "file", 
	arguments = {
		@GraphQLArgument(name = "id", optional=true),
		@GraphQLArgument(name = "where", optional=true)
	}
)
public class FileImp extends File implements GraphQLMappable, Serializable {

	@GraphQLIgnore
	private static final long serialVersionUID = 1L;
}
