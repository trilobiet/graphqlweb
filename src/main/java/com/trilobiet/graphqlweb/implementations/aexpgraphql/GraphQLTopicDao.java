package com.trilobiet.graphqlweb.implementations.aexpgraphql;

import java.util.List;
import java.util.Optional;

import com.trilobiet.graphqlweb.dao.DaoException;
import com.trilobiet.graphqlweb.dao.FieldValueQuery;
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
	public List<Topic> find(String searchterm, String sort) throws DaoException {

		TopicRequest q = new TopicRequest(host);
		GraphQLRequestEntity req = q.getFindRequest(searchterm, sort);
		return TopicResponse.getTopics(req);
	}

	@Override
	public List<Topic> find(FieldValueQuery fv) throws DaoException {
		
		TopicRequest q = new TopicRequest(host);
		GraphQLRequestEntity req = q.getFindRequest(fv);
		return TopicResponse.getTopics(req);
	}
	
	@Override
	public Optional<Topic> get(String id) throws DaoException {

		TopicRequest q = new TopicRequest(host);
		GraphQLRequestEntity req = q.getGetByIdRequest(id);
		return Optional.ofNullable(TopicResponse.getTopic(req));
	}

	@Override
	public Optional<Topic> getBySlug(String slug) throws DaoException {

		TopicRequest q = new TopicRequest(host);
		GraphQLRequestEntity req = q.getGetBySlugRequest(slug);
		List<Topic> list = TopicResponse.getTopics(req);
		if(!list.isEmpty()) return Optional.of(list.get(0));
		else return Optional.empty();
	}

	@Override
	public Optional<Section> getSectionBySlug(String slug) throws DaoException {

		TopicRequest q = new TopicRequest(host);
		GraphQLRequestEntity req = q.getSectionBySlugRequest(slug);
		List<Section> list = TopicResponse.getSections(req);
		if(!list.isEmpty())return Optional.of(list.get(0));		
		else return Optional.empty();
	}
	
}
