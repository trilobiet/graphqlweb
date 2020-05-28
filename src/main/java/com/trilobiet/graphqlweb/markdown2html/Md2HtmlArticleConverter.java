package com.trilobiet.graphqlweb.markdown2html;

import com.trilobiet.graphqlweb.datamodel.Article;

public class Md2HtmlArticleConverter<T extends Article> implements Md2HtmlConverter<T> {
	
	private final StringFunction function;
	
	public Md2HtmlArticleConverter(StringFunction f) {
		this.function = f;
	}

	@Override
	public void convert(T article) {

		if (article.getSummary() != null) {
			article.setSummary( function.apply(article.getSummary()) );
		}	
		
		if (article.getContent() != null) {
			article.setContent( function.apply(article.getContent())  );
		}	
	}

}
