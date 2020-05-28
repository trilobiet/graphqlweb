package com.trilobiet.graphqlweb.markdown2html;

import java.util.List;

public interface Md2HtmlConverter<T> {
	
	void convert(T object);
	
	default void convertList(List<T> convertibles) {
		convertibles.stream().forEach(a -> convert(a));
	}
	
}
