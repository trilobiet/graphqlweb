package com.trilobiet.graphqlweb.implementations.aexpgraphql2.section;

import java.util.List;
import java.util.Optional;

import com.trilobiet.graphqlweb.dao.DaoException;
import com.trilobiet.graphqlweb.dao.SectionDao;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.GraphQLRequest;

import io.aexp.nodes.graphql.Arguments;
import io.aexp.nodes.graphql.GraphQLRequestEntity;
import io.aexp.nodes.graphql.GraphQLResponseEntity;
import io.aexp.nodes.graphql.GraphQLTemplate;

public class GenericSectionDao<T extends SectionImp> implements SectionDao<T> {
	
	private String host;
	private Class<T> clazz;
	private Class<? extends GenericSectionList<T>> listClazz;
	
	public GenericSectionDao(String host, Class<T> clazz, Class<? extends GenericSectionList<T>> listClazz ) {
		this.host = host;
		this.clazz = clazz;
		this.listClazz = listClazz;
	}

	@Override
	public List<T> list() throws DaoException {

		Arguments args = SectionArgs.getListArgs();
		GraphQLRequestEntity requestEntity = new GraphQLRequest(host).getRequestEntity(args, listClazz);
		GenericSectionList<T> sectionList = getList(requestEntity);
		return sectionList.getSections();
	}
	
	@Override
	public Optional<T> get(String id) throws DaoException {

		Arguments args = SectionArgs.getGetByIdArgs(id);
		GraphQLRequestEntity requestEntity = new GraphQLRequest(host).getRequestEntity(args, clazz);
		T section = this.getOne(requestEntity);
		return Optional.ofNullable(section);

	}

	@Override
	public Optional<T> getBySlug(String slug) throws DaoException {
		
		Arguments args = SectionArgs.getBySlugArgs(slug);
		GraphQLRequestEntity requestEntity = new GraphQLRequest(host).getRequestEntity(args, listClazz);
		
		GenericSectionList<T> sectionList = getList(requestEntity);
		List<T> sections = sectionList.getSections();
		if(!sections.isEmpty()) return Optional.of(sections.get(0));
		else return Optional.empty();
	}
	
	
	private T getOne(GraphQLRequestEntity requestEntity) throws DaoException {
		
		GraphQLTemplate graphQLTemplate = new GraphQLTemplate();
		GraphQLResponseEntity<? extends T> responseEntity = graphQLTemplate.query(requestEntity, clazz);
		return responseEntity.getResponse();
	}
	
	private GenericSectionList<T> getList(GraphQLRequestEntity requestEntity) throws DaoException {
		
		GraphQLTemplate graphQLTemplate = new GraphQLTemplate();
		GraphQLResponseEntity<? extends GenericSectionList<T>> responseEntity = graphQLTemplate.query(requestEntity, listClazz);
		return responseEntity.getResponse();
	}

	
}
