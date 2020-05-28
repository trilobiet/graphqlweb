package com.trilobiet.graphqlweb.implementations.aexpgraphql2.topic;

import java.util.List;
import java.util.Optional;

import com.trilobiet.graphqlweb.dao.DaoException;
import com.trilobiet.graphqlweb.dao.FieldValueQuery;
import com.trilobiet.graphqlweb.dao.TopicDao;
import com.trilobiet.graphqlweb.datamodel.Section;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.GraphQLRequest;

import io.aexp.nodes.graphql.Arguments;
import io.aexp.nodes.graphql.GraphQLRequestEntity;
import io.aexp.nodes.graphql.GraphQLResponseEntity;
import io.aexp.nodes.graphql.GraphQLTemplate;

public class GenericTopicDao<T extends TopicImp> implements TopicDao<T> {
	
	private String host;
	private Class<T> clazz;
	private Class<? extends GenericTopicList<T>> listClazz;
	
	public GenericTopicDao(String host, Class<T> clazz, Class<? extends GenericTopicList<T>> listClazz ) {
		this.host = host;
		this.clazz = clazz;
		this.listClazz = listClazz;
	}

	@Override
	public List<T> list(Section section, String sort) throws DaoException {
		
		Arguments args = TopicArgs.getListBySectionArgs(section, sort);
		GraphQLRequestEntity requestEntity = new GraphQLRequest(host).getRequestEntity(args, listClazz);
		GenericTopicList<T> topicList = getTopics(requestEntity);
		return topicList.getTopics();
	}

	@Override
	public List<T> find(String searchterm, String sort) throws DaoException {

		Arguments args = TopicArgs.getFindArgs(searchterm, sort);
		GraphQLRequestEntity requestEntity = new GraphQLRequest(host).getRequestEntity(args, listClazz);
		GenericTopicList<T> topicList = getTopics(requestEntity);
		return topicList.getTopics();
	}

	@Override
	public List<T> find(FieldValueQuery q) throws DaoException {

		Arguments args = TopicArgs.getFindArgs(q);
		GraphQLRequestEntity requestEntity = new GraphQLRequest(host).getRequestEntity(args, listClazz);
		GenericTopicList<T> topicList = getTopics(requestEntity);
		return topicList.getTopics();
	}

	@Override
	public Optional<T> get(String id) throws DaoException {
		
		Arguments args = TopicArgs.getGetByIdArgs(id);
		GraphQLRequestEntity requestEntity = new GraphQLRequest(host).getRequestEntity(args, clazz);
		T topic = this.getTopic(requestEntity);
		return Optional.ofNullable(topic);
	}

	@Override
	public Optional<T> getBySlug(String slug) throws DaoException {

		Arguments args = TopicArgs.getGetBySlugArgs(slug);
		GraphQLRequestEntity requestEntity = new GraphQLRequest(host).getRequestEntity(args, listClazz);
		GenericTopicList<T> topicList = getTopics(requestEntity);
		List<T> topics = topicList.getTopics();
		if(!topics.isEmpty()) return Optional.of(topics.get(0));
		else return Optional.empty();
	}
	
	
	private T getTopic(GraphQLRequestEntity requestEntity) throws DaoException {
		
		GraphQLTemplate graphQLTemplate = new GraphQLTemplate();
		GraphQLResponseEntity<? extends T> responseEntity = graphQLTemplate.query(requestEntity, clazz);
		return responseEntity.getResponse();
	}
	
	private GenericTopicList<T> getTopics(GraphQLRequestEntity requestEntity) throws DaoException {
		
		GraphQLTemplate graphQLTemplate = new GraphQLTemplate();
		GraphQLResponseEntity<? extends GenericTopicList<T>> responseEntity = graphQLTemplate.query(requestEntity, listClazz);
		return responseEntity.getResponse();
	}
	
	
}
