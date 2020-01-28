package com.trilobiet.graphqlweb.implementations.aexpgraphql;

import com.trilobiet.graphqlweb.datamodel.Section;

import io.aexp.nodes.graphql.annotations.GraphQLArgument;
import io.aexp.nodes.graphql.annotations.GraphQLProperty;

/**
 * 
 * @author acdhirr
 *
 */
@GraphQLProperty(name="section", arguments = {@GraphQLArgument(name = "id")})
public class SectionImp extends Section implements GraphQLMappable {
	
}
