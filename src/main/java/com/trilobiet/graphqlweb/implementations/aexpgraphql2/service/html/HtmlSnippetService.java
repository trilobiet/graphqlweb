package com.trilobiet.graphqlweb.implementations.aexpgraphql2.service.html;

import java.util.Optional;

import com.trilobiet.graphqlweb.dao.DaoException;
import com.trilobiet.graphqlweb.dao.SnippetDao;
import com.trilobiet.graphqlweb.datamodel.Snippet;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.service.SnippetService;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.snippet.GenericSnippetDao;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.snippet.SnippetImp;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.snippet.SnippetList;
import com.trilobiet.graphqlweb.markdown2html.Md2HtmlConverter;

public class HtmlSnippetService<T extends Snippet> implements SnippetService<T> {
	
	private final SnippetDao<T> snippetDao;
	private final Md2HtmlConverter<T> md2HtmlConverter;
	
	/**
	 * Generic constructor
	 * 
	 * @param snippetDao
	 * @param md2HtmlConverter
	 */
	public HtmlSnippetService(SnippetDao<T> snippetDao, Md2HtmlConverter<T> md2HtmlConverter) {
		this.snippetDao = snippetDao;
		this.md2HtmlConverter = md2HtmlConverter;
	}
	
	/**
	 * Convenience constructor for use with default SnippetImp and SnippetList
	 * 
	 * @param host
	 * @param md2HtmlConverter
	 */
	@SuppressWarnings("all")
	public HtmlSnippetService(String host, Md2HtmlConverter<T> md2HtmlConverter) {
		this(new GenericSnippetDao(host, SnippetImp.class, SnippetList.class), md2HtmlConverter);
	}

	@Override
	public Optional<T> getSnippet(String name) throws DaoException {

		return snippetDao.getByName(name);
	}

	public Optional<T> getMarkdownSnippet(String name) throws Exception {
		
		Optional<T> osnip = snippetDao.getByName(name);
		if (osnip.isPresent()) md2HtmlConverter.convert(osnip.get());

		return osnip;
	}
}
