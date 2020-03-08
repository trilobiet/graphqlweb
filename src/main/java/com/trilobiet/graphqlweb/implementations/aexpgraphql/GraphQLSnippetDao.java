package com.trilobiet.graphqlweb.implementations.aexpgraphql;

import java.util.List;
import java.util.Optional;

import com.trilobiet.graphqlweb.dao.DaoException;
import com.trilobiet.graphqlweb.dao.SnippetDao;
import com.trilobiet.graphqlweb.datamodel.Snippet;

import io.aexp.nodes.graphql.GraphQLRequestEntity;

public class GraphQLSnippetDao implements SnippetDao {
	
	private String host;
	
	public GraphQLSnippetDao(String host) {
		this.host = host;
	}

	@Override
	public Optional<Snippet> get(String id) throws DaoException {

		SnippetRequest q = new SnippetRequest(host);
		GraphQLRequestEntity req = q.getGetByIdRequest(id);
		return Optional.ofNullable(SnippetResponse.getSnippet(req));
	}
	
	@Override
	public Optional<Snippet> getByName(String slug) throws DaoException {

		SnippetRequest q = new SnippetRequest(host);
		GraphQLRequestEntity req = q.getGetByNameRequest(slug);
		List<Snippet> list = SnippetResponse.getSnippets(req);
		if(!list.isEmpty()) return Optional.of(list.get(0));
		else return Optional.empty();
	}

	@Override
	public List<Snippet> list() throws DaoException {

		SnippetRequest q = new SnippetRequest(host);
		GraphQLRequestEntity req = q.getListRequest();
		return SnippetResponse.getSnippets(req);
	}
	
	
	
	
}
