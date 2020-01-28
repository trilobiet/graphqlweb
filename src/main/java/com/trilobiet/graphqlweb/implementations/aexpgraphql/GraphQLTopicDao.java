package com.trilobiet.graphqlweb.implementations.aexpgraphql;

import java.util.List;

import com.trilobiet.graphqlweb.dao.DaoException;
import com.trilobiet.graphqlweb.dao.TopicDao;
import com.trilobiet.graphqlweb.datamodel.Section;
import com.trilobiet.graphqlweb.datamodel.Topic;

import io.aexp.nodes.graphql.GraphQLRequestEntity;

/**
 * 
 * @author acdhirr
 *
 */
public class GraphQLTopicDao implements TopicDao {
	
	private String host;
	
	public GraphQLTopicDao(String host) {
		this.host = host;
	}

	@Override
	public List<Section> listSections() throws DaoException {

		TopicRequest q = new TopicRequest(host);
		GraphQLRequestEntity req = q.getListSectionsRequest();
		return TopicResponse.getSections(req);
	}

	@Override
	public List<Topic> list(Section section, String sort) throws DaoException {

		TopicRequest q = new TopicRequest(host);
		GraphQLRequestEntity req = q.getListBySectionRequest(section, sort);
		return TopicResponse.getTopics(req);
	}

	@Override
	public List<Topic> find(String name, String sort) throws DaoException {

		TopicRequest q = new TopicRequest(host);
		GraphQLRequestEntity req = q.getFindRequest(name, sort);
		return TopicResponse.getTopics(req);
	}

	@Override
	public Topic get(String id) throws DaoException {

		TopicRequest q = new TopicRequest(host);
		GraphQLRequestEntity req = q.getGetByIdRequest(id);
		return TopicResponse.getTopic(req);
	}
	
}
