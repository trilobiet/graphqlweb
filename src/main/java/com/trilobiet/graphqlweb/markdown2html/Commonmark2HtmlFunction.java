package com.trilobiet.graphqlweb.markdown2html;


import java.util.Arrays;
import java.util.List;

import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

public class Commonmark2HtmlFunction implements StringFunction {
	
	private final Parser parser;
	private final HtmlRenderer renderer;
	
	public Commonmark2HtmlFunction() {
		
		List<Extension> extensions = Arrays.asList(TablesExtension.create());
		parser = Parser.builder().extensions(extensions).build();
		renderer = HtmlRenderer.builder().extensions(extensions).build();
	}

	@Override
	public String apply(String input) {
		
		Node node = parser.parse( input );
		return renderer.render(node);
	}
}

