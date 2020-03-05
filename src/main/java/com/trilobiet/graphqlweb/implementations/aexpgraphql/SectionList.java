package com.trilobiet.graphqlweb.implementations.aexpgraphql;

import java.util.List;

import com.trilobiet.graphqlweb.datamodel.Section;

import io.aexp.nodes.graphql.annotations.GraphQLArgument;
import io.aexp.nodes.graphql.annotations.GraphQLArguments;

/**
 * 
 * @author acdhirr
 *
 */
public class SectionList implements GraphQLMappable {
	
	@GraphQLArguments({
		@GraphQLArgument(
			name = "sort",
			type = "String",
			optional = true, value = "groupNumber:asc,index:asc,name:asc"
		),
		@GraphQLArgument(
			name = "where",
			optional = true, value = "publish:true"
		)
	})
	
	private List<Section> sections;

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}
}
