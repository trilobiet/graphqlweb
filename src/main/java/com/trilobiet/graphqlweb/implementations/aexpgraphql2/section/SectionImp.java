package com.trilobiet.graphqlweb.implementations.aexpgraphql2.section;

import com.trilobiet.graphqlweb.datamodel.Section;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.GraphQLMappable;

import io.aexp.nodes.graphql.annotations.GraphQLArgument;
import io.aexp.nodes.graphql.annotations.GraphQLProperty;

/**
 * 
 * @author acdhirr
 *
 */
@GraphQLProperty(
	name="section", 
	arguments = {
		@GraphQLArgument(name = "id")
	}
)
public class SectionImp extends Section implements GraphQLMappable {
	
}
