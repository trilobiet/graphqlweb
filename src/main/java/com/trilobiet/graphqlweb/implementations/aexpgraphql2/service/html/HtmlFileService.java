package com.trilobiet.graphqlweb.implementations.aexpgraphql2.service.html;

import java.util.List;
import java.util.Optional;

import com.trilobiet.graphqlweb.dao.DaoException;
import com.trilobiet.graphqlweb.dao.FileDao;
import com.trilobiet.graphqlweb.datamodel.File;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.GraphQLFieldValueQuery;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.file.FileImp;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.file.FileList;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.file.GenericFileDao;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.service.FileService;

public class HtmlFileService<T extends File> implements FileService<T> {
	
	private final FileDao<T> fileDao;
	
	/**
	 * Generic constructor
	 * 
	 * @param fileDao
	 */
	public HtmlFileService(FileDao<T> fileDao) {
		this.fileDao = fileDao;
	}
	
	/**
	 * Convenience constructor for use with default FileImp and FileList
	 * 
	 * @param host
	 */
	@SuppressWarnings("all")
	public HtmlFileService(String host) {
		this(new GenericFileDao(host, FileImp.class, FileList.class));
	}
	

	@Override
	public Optional<T> get(String id) throws DaoException {
		return fileDao.get(id);
	}

	@Override
	public List<T> getByName(String name) throws DaoException {
		return fileDao.getByName( name );
	}

	@Override
	public Optional<T> getFirstWithName(String name) throws DaoException {
		
		List<T> files = fileDao.getByName( name );
		if (!files.isEmpty()) return Optional.of(files.get(0));
		else return Optional.empty(); 
	}
	
	@Override
	public List<T> getByFieldValue(String field, String value) throws DaoException {
		
		GraphQLFieldValueQuery q = 
			new GraphQLFieldValueQuery.Builder(field,value)
			.build();
		
		return fileDao.find(q);
	}
	
	
}
