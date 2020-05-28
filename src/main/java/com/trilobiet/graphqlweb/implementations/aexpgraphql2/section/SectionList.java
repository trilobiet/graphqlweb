package com.trilobiet.graphqlweb.implementations.aexpgraphql2.section;

import java.util.List;

import io.aexp.nodes.graphql.annotations.GraphQLArgument;
import io.aexp.nodes.graphql.annotations.GraphQLArguments;

/**
 * 
 * @author acdhirr
 *
 */
public class SectionList implements GenericSectionList<SectionImp> {
	
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
	
	private List<SectionImp> sections;

	public List<SectionImp> getSections() {
		return sections;
	}

	public void setSections(List<SectionImp> sections) {
		this.sections = sections;
	}
}
