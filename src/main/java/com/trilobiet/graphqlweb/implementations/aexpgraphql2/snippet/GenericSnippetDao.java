package com.trilobiet.graphqlweb.implementations.aexpgraphql2.snippet;

import java.util.List;
import java.util.Optional;

import com.trilobiet.graphqlweb.dao.DaoException;
import com.trilobiet.graphqlweb.dao.SnippetDao;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.GraphQLRequest;

import io.aexp.nodes.graphql.Arguments;
import io.aexp.nodes.graphql.GraphQLRequestEntity;
import io.aexp.nodes.graphql.GraphQLResponseEntity;
import io.aexp.nodes.graphql.GraphQLTemplate;

public class GenericSnippetDao<T extends SnippetImp, U extends GenericSnippetList<T>> implements SnippetDao<T> {
	
	private String host;
	private Class<? extends T> clazz;
	private Class<? extends GenericSnippetList<T>> listClazz;
	
	public GenericSnippetDao(String host, Class<? extends T> clazz, Class<? extends GenericSnippetList<T>> listClazz ) {
		this.host = host;
		this.clazz = clazz;
		this.listClazz = listClazz;
	}

	@Override
	public List<T> list() throws DaoException {

		Arguments args = SnippetArgs.getListArgs();
		GraphQLRequestEntity requestEntity = new GraphQLRequest(host).getRequestEntity(args, listClazz);
		GenericSnippetList<T> snippetList = getSnippets(requestEntity);
		return snippetList.getSnippets();
	}

	@Override
	public Optional<T> get(String id) throws DaoException {

		Arguments args = SnippetArgs.getGetByIdArgs(id);
		GraphQLRequestEntity requestEntity = new GraphQLRequest(host).getRequestEntity(args, clazz);
		T snippet = getSnippet(requestEntity);
		return Optional.ofNullable(snippet);
	}

	@Override
	public Optional<T> getByName(String name) throws DaoException {
		
		Arguments args = SnippetArgs.getGetByNameArgs(name);
		GraphQLRequestEntity requestEntity = new GraphQLRequest(host).getRequestEntity(args, listClazz);
		GenericSnippetList<T> snippetList = getSnippets(requestEntity);
		List<T> snippets = snippetList.getSnippets();
		if(!snippets.isEmpty()) return Optional.of(snippets.get(0));
		else return Optional.empty();
	}

	private T getSnippet(GraphQLRequestEntity requestEntity) throws DaoException {
		
		GraphQLTemplate graphQLTemplate = new GraphQLTemplate();
		GraphQLResponseEntity<? extends T> responseEntity = graphQLTemplate.query(requestEntity, clazz);
		return responseEntity.getResponse();
	}
	
	private GenericSnippetList<T> getSnippets(GraphQLRequestEntity requestEntity) throws DaoException {
		
		GraphQLTemplate graphQLTemplate = new GraphQLTemplate();
		GraphQLResponseEntity<? extends GenericSnippetList<T>> responseEntity = graphQLTemplate.query(requestEntity, listClazz);
		return responseEntity.getResponse();
	}

}
