package com.trilobiet.graphqlweb.implementations.aexpgraphql2.service.html;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import com.trilobiet.graphqlweb.dao.DaoException;
import com.trilobiet.graphqlweb.dao.FieldValueQuery.MatchType;
import com.trilobiet.graphqlweb.datamodel.Article;
import com.trilobiet.graphqlweb.datamodel.ArticleOutline;
import com.trilobiet.graphqlweb.datamodel.Category;
import com.trilobiet.graphqlweb.datamodel.Topic;
import com.trilobiet.graphqlweb.datamodel.comparator.ArticleComparator;
import com.trilobiet.graphqlweb.datamodel.comparator.ArticleTitleComparator;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.GraphQLFieldValueQuery;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.article.ArticleImp;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.article.ArticleList;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.article.GenericArticleDao;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.service.ArticleService;
import com.trilobiet.graphqlweb.markdown2html.Md2HtmlConverter;

public class HtmlArticleService<T extends ArticleImp> implements ArticleService<T> {
	
	private final GenericArticleDao<T> articleDao;
	private final Md2HtmlConverter<T> md2HtmlConverter;
	
	/**
	 * Generic constructor
	 * 
	 * @param articleDao
	 * @param md2HtmlConverter
	 */
	public HtmlArticleService(GenericArticleDao<T> articleDao, Md2HtmlConverter<T> md2HtmlConverter) {
		this.articleDao = articleDao;
		this.md2HtmlConverter = md2HtmlConverter;
	}
	
	/**
	 * Convenience constructor for use with default ArticleImp and ArticleList
	 * 
	 * @param host
	 * @param md2HtmlConverter
	 */
	@SuppressWarnings("all")
	public HtmlArticleService(String host, Md2HtmlConverter<T> md2HtmlConverter) {
		this(new GenericArticleDao(host, ArticleImp.class, ArticleList.class), md2HtmlConverter);
	}

	@Override
	public List<Category> getCategories() throws DaoException {

		return articleDao.listCategories();
	}

	@Override
	public List<T> getArticlesByTopic(Topic topic) throws DaoException {
		
		List<T> articles = articleDao.list(topic, "index");
		Collections.sort(articles,new ArticleComparator());
		md2HtmlConverter.convertList(articles);
		return articles;
	}
	
	@Override
	public Optional<T> getArticleBySlug(String slug) throws DaoException {

		Optional<T> oa = articleDao.getBySlug(slug);
		oa.ifPresent( a -> md2HtmlConverter.convert(a) );
		return oa;
	}
	
	@Override
	public List<T> getByFieldValue(String field, String value) throws DaoException {
		
		GraphQLFieldValueQuery q = 
				new GraphQLFieldValueQuery.Builder(field,value)
				.setSort("index:asc")
				.build();
		
		List<T> articles = articleDao.find(q);
		md2HtmlConverter.convertList(articles);
		
		return articles;
	}

	@Override
	public List<T> getByFieldContainsValue(String field, String value) throws DaoException {
		
		GraphQLFieldValueQuery q = 
				new GraphQLFieldValueQuery.Builder(field,value)
				.setMatchType(MatchType.CONTAINS)
				.setSort("index:asc")
				.build();
		
		List<T> articles = articleDao.find(q);
		
		md2HtmlConverter.convertList(articles);
		
		return articles;
	}

	
	/**
	 * Find articles that have at least one of the inputed tags as their own tag
	 * @param tags
	 * @return
	 * @throws DaoException
	 */
	@Override
	public Set<ArticleOutline> getLinked(Article article) throws DaoException {

		Set<ArticleOutline> articles = new HashSet<>(); 
		List<String> tagList = tagList( article );

		tagList.stream()
		.filter(tag -> tag.trim().length()>1) // matches on empty tags will return the entire collection 
		.forEach( tag -> {
			try {
				articles.addAll(
					getByFieldContainsValue("tags",tag)
				);
			} catch (DaoException e) { /* just forget it*/	}
		});
		
		// remove article itself from linked list
		articles.removeIf(a->a.getId().equals(article.getId()));

		TreeSet<ArticleOutline> sorted = new TreeSet<>(new ArticleTitleComparator());
		sorted.addAll(articles);
		
		return sorted;
	}
	
	private List<String> tagList(Article article) {
		
		List<String> taglist = new ArrayList<>();

		String tags = article.getTags();

		if(tags != null) {
			String[] artags = tags.split(",");
			taglist.addAll( Arrays.asList(artags) );
		}
		return taglist;
	}
	
	
}
