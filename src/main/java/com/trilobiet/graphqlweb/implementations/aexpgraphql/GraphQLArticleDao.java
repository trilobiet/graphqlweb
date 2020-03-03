package com.trilobiet.graphqlweb.implementations.aexpgraphql;

import java.util.List;
import java.util.Optional;

import com.trilobiet.graphqlweb.dao.ArticleDao;
import com.trilobiet.graphqlweb.dao.DaoException;
import com.trilobiet.graphqlweb.dao.FieldValueQuery;
import com.trilobiet.graphqlweb.dao.FieldValueQuery.MatchType;
import com.trilobiet.graphqlweb.datamodel.Article;
import com.trilobiet.graphqlweb.datamodel.Category;
import com.trilobiet.graphqlweb.datamodel.Topic;

import io.aexp.nodes.graphql.GraphQLRequestEntity;

/**
 * 
 * @author acdhirr
 *
 */
public class GraphQLArticleDao implements ArticleDao {
	
	private String host;
	
	public GraphQLArticleDao(String host) {
		this.host = host;
	}

	@Override
	public List<Category> listCategories() throws DaoException {
		
		ArticleRequest q = new ArticleRequest(host);
		GraphQLRequestEntity req = q.getListCategoriesRequest();
		return ArticleResponse.getCategories(req);
	}
	
	@Override
	public List<Article> list(Topic topic, String sort) throws DaoException {

		ArticleRequest q = new ArticleRequest(host);
		GraphQLRequestEntity req = q.getListByTopicRequest(topic, sort);
		return ArticleResponse.getArticles(req);
	}

	@Override
	public List<Article> list(Category category, String sort) throws DaoException {

		ArticleRequest q = new ArticleRequest(host);
		GraphQLRequestEntity req = q.getListByCategoryRequest(category, sort);
		return ArticleResponse.getArticles(req);
	}
	
	@Override
	public List<Article> find(String searchterm, String sort) throws DaoException {
		
		ArticleRequest q = new ArticleRequest(host);
		GraphQLRequestEntity req = q.getFindRequest(searchterm, sort);
		return ArticleResponse.getArticles(req);
	}

	@Override
	public List<Article> find(FieldValueQuery fv) throws DaoException {
		
		ArticleRequest q = new ArticleRequest(host);
		GraphQLRequestEntity req = q.getFindRequest(fv);
		
		System.out.println(req);
		return ArticleResponse.getArticles(req);
	}
	
	@Override
	public Optional<Article> get(String id) throws DaoException {

		ArticleRequest q = new ArticleRequest(host);
		GraphQLRequestEntity req = q.getGetByIdRequest(id);
		return Optional.ofNullable(ArticleResponse.getArticle(req));
	}
	
	@Override
	public Optional<Article> getBySlug(String slug) throws DaoException {

		ArticleRequest q = new ArticleRequest(host);
		GraphQLRequestEntity req = q.getGetBySlugRequest(slug);
		return Optional.ofNullable(ArticleResponse.getArticles(req).get(0));
	}
	
	
	
	public static void main(String...strings ) throws DaoException {
		
		String url = "your.url.com";
		
		GraphQLArticleDao dao = new GraphQLArticleDao(url);
		/*
		System.out.println(dao.get("5e1dce7d2bb811000b64ef4f"));
		Category cat = new Category();
		cat.setName("FUNDERS");
		System.out.println(dao.list(cat, "name:asc"));
		*/
		
		GraphQLFieldValueQuery q = 
				new GraphQLFieldValueQuery.Builder("content", "and")
				.setMatchType(MatchType.CONTAINS)
				.setSort("index:DESC")
				.build();
		
		List<Article> r = dao.find(q);
		r.forEach(System.out::println);
		
	}

}
