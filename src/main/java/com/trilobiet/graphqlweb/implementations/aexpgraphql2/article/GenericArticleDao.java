package com.trilobiet.graphqlweb.implementations.aexpgraphql2.article;

import java.util.List;
import java.util.Optional;

import com.trilobiet.graphqlweb.dao.ArticleDao;
import com.trilobiet.graphqlweb.dao.DaoException;
import com.trilobiet.graphqlweb.dao.FieldValueQuery;
import com.trilobiet.graphqlweb.datamodel.Category;
import com.trilobiet.graphqlweb.datamodel.Topic;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.GraphQLRequest;

import io.aexp.nodes.graphql.Arguments;
import io.aexp.nodes.graphql.GraphQLRequestEntity;
import io.aexp.nodes.graphql.GraphQLResponseEntity;
import io.aexp.nodes.graphql.GraphQLTemplate;

public class GenericArticleDao<T extends ArticleImp, U extends GenericArticleList<T>> implements ArticleDao<T> {
		
	private String host;
	private Class<? extends T> clazz;
	private Class<? extends GenericArticleList<T>> listClazz;
	
	public GenericArticleDao(String host, Class<? extends T> clazz, Class<? extends GenericArticleList<T>> listClazz ) {
		this.host = host;
		this.clazz = clazz;
		this.listClazz = listClazz;
	}

	@Override
	public List<Category> listCategories() throws DaoException {
		Arguments args = ArticleArgs.getListCategoriesArgs();
		GraphQLRequestEntity requestEntity = new GraphQLRequest(host).getRequestEntity(args, CategoryList.class);
		CategoryList categoryList = getCategories(requestEntity);
		return categoryList.getCategories();
	}

	@Override
	public List<T> list(Category category, String sort) throws DaoException {
		
		Arguments args = ArticleArgs.getListByCategoryArgs(category, sort);
		GraphQLRequestEntity requestEntity = new GraphQLRequest(host).getRequestEntity(args, listClazz);
		GenericArticleList<T> articleList = getArticles(requestEntity);
		return articleList.getArticles();
	}

	@Override
	public List<T> list(Topic topic, String sort) throws DaoException {
		
		Arguments args = ArticleArgs.listByTopicArgs(topic, sort);
		GraphQLRequestEntity requestEntity = new GraphQLRequest(host).getRequestEntity(args, listClazz);
		GenericArticleList<T> articleList = getArticles(requestEntity);
		return articleList.getArticles();
	}
	
	@Override
	public List<T> find(String searchterm, String sort) throws DaoException {

		Arguments args = ArticleArgs.getFindArgs(searchterm, sort);
		GraphQLRequestEntity requestEntity = new GraphQLRequest(host).getRequestEntity(args, listClazz);
		GenericArticleList<T> articleList = getArticles(requestEntity);
		return articleList.getArticles();
	}

	@Override
	public List<T> find(FieldValueQuery q) throws DaoException {

		Arguments args = ArticleArgs.getFindArgs(q);
		GraphQLRequestEntity requestEntity = new GraphQLRequest(host).getRequestEntity(args, listClazz);
		GenericArticleList<T> articleList = getArticles(requestEntity);
		return articleList.getArticles();
	}	
	
	@Override
	public Optional<T> get(String id) throws DaoException {
		
		Arguments args = ArticleArgs.getByIdArgs(id);
		GraphQLRequestEntity requestEntity = new GraphQLRequest(host).getRequestEntity(args, clazz);
		T article = getArticle(requestEntity);
		return Optional.ofNullable(article);
	}

	@Override
	public Optional<T> getBySlug(String slug) throws DaoException {
		
		Arguments args = ArticleArgs.getGetBySlugArgs(slug);
		GraphQLRequestEntity requestEntity = new GraphQLRequest(host).getRequestEntity(args, listClazz);		
		GenericArticleList<T> articleList = getArticles(requestEntity);
		List<T> articles = articleList.getArticles();
		if(!articles.isEmpty()) return Optional.of(articles.get(0));
		else return Optional.empty();
	}
	

	private CategoryList getCategories(GraphQLRequestEntity requestEntity) throws DaoException {
		
		GraphQLTemplate graphQLTemplate = new GraphQLTemplate();
		GraphQLResponseEntity<CategoryList> responseEntity = graphQLTemplate.query(requestEntity, CategoryList.class);
		return responseEntity.getResponse();
	}
	
	private T getArticle(GraphQLRequestEntity requestEntity) throws DaoException {
		
		GraphQLTemplate graphQLTemplate = new GraphQLTemplate();
		GraphQLResponseEntity<? extends T> responseEntity = graphQLTemplate.query(requestEntity, clazz);
		return responseEntity.getResponse();
	}
	
	private GenericArticleList<T> getArticles(GraphQLRequestEntity requestEntity) throws DaoException {
		
		GraphQLTemplate graphQLTemplate = new GraphQLTemplate();
		GraphQLResponseEntity<? extends GenericArticleList<T>> responseEntity = graphQLTemplate.query(requestEntity, listClazz);
		return responseEntity.getResponse();
	}
		
}
