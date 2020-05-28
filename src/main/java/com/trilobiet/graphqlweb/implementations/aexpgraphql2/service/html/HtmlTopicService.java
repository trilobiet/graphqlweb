package com.trilobiet.graphqlweb.implementations.aexpgraphql2.service.html;

import java.util.List;
import java.util.Optional;

import com.trilobiet.graphqlweb.dao.DaoException;
import com.trilobiet.graphqlweb.dao.FieldValueQuery.MatchType;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.GraphQLFieldValueQuery;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.service.TopicService;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.topic.GenericTopicDao;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.topic.TopicImp;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.topic.TopicList;
import com.trilobiet.graphqlweb.markdown2html.Md2HtmlConverter;

public class HtmlTopicService<T extends TopicImp> implements TopicService<T> {
	
	private final GenericTopicDao<T> topicDao;
	private final Md2HtmlConverter<T> md2HtmlConverter;
	
	/**
	 * Generic constructor
	 * 
	 * @param genericTopicDao
	 * @param md2HtmlConverter
	 */
	public HtmlTopicService(GenericTopicDao<T> genericTopicDao, Md2HtmlConverter<T> md2HtmlConverter) {
		this.topicDao = genericTopicDao;
		this.md2HtmlConverter = md2HtmlConverter;
	}

	/**
	 * Convenience constructor for use with default TopicImp and TopicList
	 * 
	 * @param host
	 * @param md2HtmlConverter
	 */
	@SuppressWarnings("all")
	public HtmlTopicService(String host, Md2HtmlConverter<T> md2HtmlConverter) {
		this( new GenericTopicDao(host, TopicImp.class, TopicList.class), md2HtmlConverter);
	}
	
	@Override
	// @Cacheable(value="topicsCache",key="#slug")
	public Optional<T> getTopicBySlug(String slug) throws DaoException {
		
		Optional<T> otopic = topicDao.getBySlug(slug);
		if (otopic.isPresent())	md2HtmlConverter.convert(otopic.get());
		return otopic;
	}

	@Override
	public List<T> getByFieldValue(String field, String value) throws DaoException {

		GraphQLFieldValueQuery q = 
				new GraphQLFieldValueQuery.Builder(field,value)
				.setSort("index:asc")
				.build();
		
		List<T> topics = topicDao.find(q);
		md2HtmlConverter.convertList(topics);
		
		return topics;
	}

	@Override
	public List<T> getByFieldContainsValue(String field, String value) throws DaoException {

		GraphQLFieldValueQuery q = 
				new GraphQLFieldValueQuery.Builder(field,value)
				.setSort("index:asc")
				.setMatchType(MatchType.CONTAINS)
				.build();
		
		List<T> topics = topicDao.find(q);
		md2HtmlConverter.convertList(topics);
		
		return topics;
	}
	
}
