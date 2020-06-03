package com.trilobiet.graphqlweb.implementations.aexpgraphql2.service.html;

import java.util.List;
import java.util.Optional;

import com.trilobiet.graphqlweb.dao.DaoException;
import com.trilobiet.graphqlweb.dao.SectionDao;
import com.trilobiet.graphqlweb.datamodel.Section;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.section.GenericSectionDao;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.section.SectionImp;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.section.SectionList;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.service.SectionService;
import com.trilobiet.graphqlweb.markdown2html.Md2HtmlConverter;

public class HtmlSectionService<T extends Section> implements SectionService<T> {
	
	private final SectionDao<T> sectionDao;
	private final Md2HtmlConverter<T> md2HtmlConverter;
	
	/**
	 * Generic constructor
	 * 
	 * @param sectionDao
	 * @param md2HtmlConverter
	 */
	public HtmlSectionService(SectionDao<T> sectionDao, Md2HtmlConverter<T> md2HtmlConverter) {
		this.sectionDao = sectionDao;
		this.md2HtmlConverter = md2HtmlConverter;
	}
	
	/**
	 * Convenience constructor for use with default SectionImp and SectionList
	 * 
	 * @param host
	 * @param md2HtmlConverter
	 */
	@SuppressWarnings("all")
	public HtmlSectionService(String host, Md2HtmlConverter<T> md2HtmlConverter) {
		this(new GenericSectionDao(host, SectionImp.class, SectionList.class), md2HtmlConverter);
	}

	@Override
	public List<T> getSections() throws DaoException {

		List<T> sections = sectionDao.list();
		md2HtmlConverter.convertList(sections);
		return sections;
	}

	@Override
	public Optional<T> getSectionBySlug(String slug) throws DaoException {

		Optional<T> osection = sectionDao.getBySlug(slug);
		if (osection.isPresent()) md2HtmlConverter.convert(osection.get());
		return osection;
	}

}
