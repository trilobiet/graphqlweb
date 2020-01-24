package com.trilobiet.graphqlweb.implementations.aexpgraphql;

import java.util.Collection;

import com.trilobiet.graphqlweb.dao.ArticleDao;
import com.trilobiet.graphqlweb.datamodel.Article;
import com.trilobiet.graphqlweb.datamodel.Category;
import com.trilobiet.graphqlweb.datamodel.DaoException;
import com.trilobiet.graphqlweb.datamodel.Topic;

import io.aexp.nodes.graphql.GraphQLRequestEntity;

public class GraphQLArticleDao implements ArticleDao {
	
	private String host;
	
	public GraphQLArticleDao(String graphqlHost) {
		this.host = graphqlHost;
	}

	@Override
	public Collection<Category> listCategories() throws DaoException {
		
		ArticleRequest q = new ArticleRequest(host);
		GraphQLRequestEntity req = q.getListCategoriesRequest();
		return new ArticleResponse(req).getCategories();
		
	}
	
	@Override
	public Collection<Article> list(Topic topic, String sort) throws DaoException {

		ArticleRequest q = new ArticleRequest(host);
		GraphQLRequestEntity req = q.getListByTopicRequest(topic, sort);
		return new ArticleResponse(req).getArticles();
	}

	@Override
	public Collection<Article> list(Category category, String sort) throws DaoException {

		ArticleRequest q = new ArticleRequest(host);
		GraphQLRequestEntity req = q.getListByCategoryRequest(category, sort);
		return new ArticleResponse(req).getArticles();
	}
	
	@Override
	public Collection<Article> find(String searchterm, String sort) throws DaoException {
		
		ArticleRequest q = new ArticleRequest(host);
		GraphQLRequestEntity req = q.getFindRequest(searchterm, sort);
		return new ArticleResponse(req).getArticles();
	}
	
	@Override
	public Article get(String id) throws DaoException {

		ArticleRequest q = new ArticleRequest(host);
		GraphQLRequestEntity req = q.getGetByIdRequest(id);
		return new ArticleResponse(req).getArticle();
	}

	public static void main(String...strings) throws DaoException {
		
		GraphQLArticleDao dao = new GraphQLArticleDao("http://localhost:1337/graphql");
		
		Category cat = new Category();
		cat.setName("FUNDERS");
		
		Collection<Article> r = dao.list(cat, "title:asc");
		System.out.println(r);
	}
	
}
