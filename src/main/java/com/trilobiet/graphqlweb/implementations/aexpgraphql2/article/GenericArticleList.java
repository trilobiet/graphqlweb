package com.trilobiet.graphqlweb.implementations.aexpgraphql2.article;

import java.util.List;

import com.trilobiet.graphqlweb.datamodel.Article;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.GraphQLMappable;

public interface GenericArticleList<T extends Article> extends GraphQLMappable {

	public List<T> getArticles();
	public void setArticles(List<T> articles);
}