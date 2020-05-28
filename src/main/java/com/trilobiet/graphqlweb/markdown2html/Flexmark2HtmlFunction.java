package com.trilobiet.graphqlweb.markdown2html;

import java.util.Arrays;

import com.vladsch.flexmark.ext.attributes.AttributesExtension;
import com.vladsch.flexmark.ext.footnotes.FootnoteExtension;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.ext.typographic.TypographicExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.parser.ParserEmulationProfile;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.MutableDataSet;

public class Flexmark2HtmlFunction implements StringFunction {
	
	protected final Parser parser;
	protected final HtmlRenderer renderer;

	public Flexmark2HtmlFunction() {
		
		MutableDataSet options = new MutableDataSet();
		
		options.setFrom(ParserEmulationProfile.COMMONMARK);
		options.set(Parser.EXTENSIONS, Arrays.asList(
			FootnoteExtension.create(),
			TablesExtension.create(),
			TypographicExtension.create(),
			// allow for attributes like [Link name](https://www.your.url){target="_blank" title="interesting"}
			AttributesExtension.create()  
		));
		
		//options.set(Parser.HEADING_NO_LEAD_SPACE, true);
		options.set(Parser.HEADING_NO_ATX_SPACE, true);
		options.set(HtmlRenderer.GENERATE_HEADER_ID, true);
		
		parser = Parser.builder(options).build();
		renderer = HtmlRenderer.builder(options).build();
	}
	
	@Override
	public String apply(String input) {
		
		Node node = parser.parse( input );
		return renderer.render(node);
	}

}
