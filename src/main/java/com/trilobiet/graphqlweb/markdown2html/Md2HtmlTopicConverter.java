package com.trilobiet.graphqlweb.markdown2html;

import com.trilobiet.graphqlweb.datamodel.Topic;

public class Md2HtmlTopicConverter<T extends Topic> implements Md2HtmlConverter<T> {
	
	private final StringFunction function;
	
	public Md2HtmlTopicConverter(StringFunction f) {
		this.function = f;
	}

	@Override
	public void convert(T topic) {

		if (topic.getDescription() != null) {
			topic.setDescription( function.apply(topic.getDescription()) );
		}	
	}

}
