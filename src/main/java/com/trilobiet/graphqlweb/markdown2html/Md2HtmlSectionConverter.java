package com.trilobiet.graphqlweb.markdown2html;

import com.trilobiet.graphqlweb.datamodel.Section;

public class Md2HtmlSectionConverter<T extends Section> implements Md2HtmlConverter<T> {
	
	private final StringFunction function;
	
	public Md2HtmlSectionConverter(StringFunction f) {
		this.function = f;
	}

	@Override
	public void convert(T section) {

		if (section.getDescription() != null) {
			section.setDescription( function.apply(section.getDescription()) );
		}	
		
		section.getTranslations().stream()
		.forEach(t -> {
			if (t.getDescription() != null) 
				t.setDescription(function.apply(t.getDescription()));	
		});
	}

}
