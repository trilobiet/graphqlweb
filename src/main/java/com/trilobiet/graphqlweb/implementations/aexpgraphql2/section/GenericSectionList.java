package com.trilobiet.graphqlweb.implementations.aexpgraphql2.section;

import java.util.List;

import com.trilobiet.graphqlweb.datamodel.Section;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.GraphQLMappable;

public interface GenericSectionList<T extends Section> extends GraphQLMappable {

	public List<T> getSections();
	public void setSections(List<T> sections);
}

