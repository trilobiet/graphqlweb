package com.trilobiet.graphqlweb.implementations.aexpgraphql2.file;

import java.util.List;
import java.util.Optional;

import com.trilobiet.graphqlweb.dao.DaoException;
import com.trilobiet.graphqlweb.dao.FieldValueQuery;
import com.trilobiet.graphqlweb.dao.FileDao;
import com.trilobiet.graphqlweb.datamodel.File;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.GraphQLRequest;

import io.aexp.nodes.graphql.Arguments;
import io.aexp.nodes.graphql.GraphQLRequestEntity;
import io.aexp.nodes.graphql.GraphQLResponseEntity;
import io.aexp.nodes.graphql.GraphQLTemplate;

public class GenericFileDao<T extends FileImp, U extends GenericFileList<T>> implements FileDao<T> {
	
	private String host;
	private Class<? extends T> clazz;
	private Class<? extends GenericFileList<T>> listClazz;
	
	public GenericFileDao(String host, Class<? extends T> clazz, Class<? extends GenericFileList<T>> listClazz ) {
		this.host = host;
		this.clazz = clazz;
		this.listClazz = listClazz;
	}

	@Override
	public Optional<T> get(String id) throws DaoException {

		Arguments args = FileArgs.getGetByIdArgs(id);
		GraphQLRequestEntity requestEntity = new GraphQLRequest(host).getRequestEntity(args, clazz);
		T file = getFile(requestEntity);
		return Optional.ofNullable(file);
	}

	@Override
	public List<T> getByName(String name) throws DaoException {

		Arguments args = FileArgs.getGetByNameArgs(name);
		GraphQLRequestEntity requestEntity = new GraphQLRequest(host).getRequestEntity(args, listClazz);
		GenericFileList<T> fileList = getFiles(requestEntity);
		return fileList.getFiles();
	}

	@Override
	public List<T> find(FieldValueQuery q) throws DaoException {

		Arguments args = FileArgs.getFindArgs(q);
		GraphQLRequestEntity requestEntity = new GraphQLRequest(host).getRequestEntity(args, listClazz);
		GenericFileList<T> fileList = getFiles(requestEntity);
		return fileList.getFiles();
	}
	
	
	private T getFile(GraphQLRequestEntity requestEntity) throws DaoException {
		
		GraphQLTemplate graphQLTemplate = new GraphQLTemplate();
		GraphQLResponseEntity<? extends T> responseEntity = graphQLTemplate.query(requestEntity, clazz);
		return responseEntity.getResponse();
	}
	
	private GenericFileList<T> getFiles(GraphQLRequestEntity requestEntity) throws DaoException {
		
		GraphQLTemplate graphQLTemplate = new GraphQLTemplate();
		GraphQLResponseEntity<? extends GenericFileList<T>> responseEntity = graphQLTemplate.query(requestEntity, listClazz);
		return responseEntity.getResponse();
	}

}
