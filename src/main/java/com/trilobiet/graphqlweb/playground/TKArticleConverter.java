package com.trilobiet.graphqlweb.playground;

import com.trilobiet.graphqlweb.markdown2html.Md2HtmlArticleConverter;
import com.trilobiet.graphqlweb.markdown2html.StringFunction;

class TKArticleConverter<T extends TKArticle> extends Md2HtmlArticleConverter<T> {

	public TKArticleConverter(StringFunction f) {
		super(f);
	}

	@Override
	public void convert(T article) {
		
		super.convert(article);
		// add more conversions if needed
	}
	
	
}
