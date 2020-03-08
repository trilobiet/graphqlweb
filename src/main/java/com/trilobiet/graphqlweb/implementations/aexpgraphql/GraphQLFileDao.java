package com.trilobiet.graphqlweb.implementations.aexpgraphql;

import java.util.List;
import java.util.Optional;

import com.trilobiet.graphqlweb.dao.DaoException;
import com.trilobiet.graphqlweb.dao.FieldValueQuery;
import com.trilobiet.graphqlweb.dao.FileDao;
import com.trilobiet.graphqlweb.datamodel.Article;
import com.trilobiet.graphqlweb.datamodel.File;

import io.aexp.nodes.graphql.GraphQLRequestEntity;

public class GraphQLFileDao implements FileDao {
	
	private String host;
	
	public GraphQLFileDao(String host) {
		this.host = host;
	}

	@Override
	public Optional<File> get(String id) throws DaoException {

		FileRequest q = new FileRequest(host);
		GraphQLRequestEntity req = q.getGetByIdRequest(id);
		return Optional.ofNullable(FileResponse.getFile(req));
	}
	
	@Override
	public Optional<File> getByName(String slug) throws DaoException {

		FileRequest q = new FileRequest(host);
		GraphQLRequestEntity req = q.getGetByNameRequest(slug);
		return Optional.ofNullable(FileResponse.getFiles(req).get(0));
	}

	@Override
	public List<File> find(FieldValueQuery fv) throws DaoException {
	
		FileRequest q = new FileRequest(host);
		GraphQLRequestEntity req = q.getFindRequest(fv);
		return FileResponse.getFiles(req);
	}

	
	
	
	
}
