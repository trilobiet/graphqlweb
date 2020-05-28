package com.trilobiet.graphqlweb.markdown2html;

import com.trilobiet.graphqlweb.datamodel.Snippet;

public class Md2HtmlSnippetConverter<T extends Snippet> implements Md2HtmlConverter<T> {
	
	private final StringFunction function;
	
	public Md2HtmlSnippetConverter(StringFunction f) {
		this.function = f;
	}

	@Override
	public void convert(T snippet) {

		if(snippet.getCode() != null) {
			snippet.setCode( function.apply(snippet.getCode()) );
		}
	}

}
