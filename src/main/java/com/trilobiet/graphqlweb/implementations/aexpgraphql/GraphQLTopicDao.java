package com.trilobiet.graphqlweb.implementations.aexpgraphql;

import java.util.Collection;

import com.trilobiet.graphqlweb.dao.TopicDao;
import com.trilobiet.graphqlweb.datamodel.Category;
import com.trilobiet.graphqlweb.datamodel.Section;
import com.trilobiet.graphqlweb.datamodel.Topic;

public class GraphQLTopicDao implements TopicDao {
	
	private String graphqlHost;
	
	public GraphQLTopicDao(String graphqlHost) {
		this.graphqlHost = graphqlHost;
	}

	@Override
	public Collection<Section> listSections() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Topic> list(Section section, String sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Topic> list(Category category, String sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Topic> find(String where, String sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Topic get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
