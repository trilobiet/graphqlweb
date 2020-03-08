package com.trilobiet.graphqlweb.implementations.aexpgraphql;

import java.util.List;
import java.util.Optional;

import com.trilobiet.graphqlweb.dao.DaoException;
import com.trilobiet.graphqlweb.dao.SnippetDao;
import com.trilobiet.graphqlweb.datamodel.Article;
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
		return Optional.ofNullable(SnippetResponse.getSnippets(req).get(0));
	}

	@Override
	public List<Snippet> list() throws DaoException {

		SnippetRequest q = new SnippetRequest(host);
		GraphQLRequestEntity req = q.getListRequest();
		return SnippetResponse.getSnippets(req);
	}
	
	
	
	
}