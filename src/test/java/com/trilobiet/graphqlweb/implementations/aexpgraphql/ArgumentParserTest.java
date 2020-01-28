package com.trilobiet.graphqlweb.implementations.aexpgraphql;

import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import io.aexp.nodes.graphql.InputObject;

/**
 * 
 * @author acdhirr
 *
 */
public class ArgumentParserTest {
	
	@Test
	public void test_parse_from_string() throws JSONException {
		
		String testQuery = "tags_contains:\"funder\", summary_contains:\"research\"";
		
		InputObject<String> ref = new InputObject.Builder<String>()			
				.put("tags_contains", "\"funder\"")
				.put("summary_contains", "\"research\"")
				.build();

		InputObject<String> obj = new ArgumentParser().parseWhere( testQuery );
		
		JSONAssert.assertEquals(obj.getMap().toString(), ref.getMap().toString(), true);
	}
	
	@Test
	public void test_parse_from_empty_string() throws JSONException {
		
		InputObject<String> obj = new ArgumentParser().parseWhere("");
		JSONAssert.assertEquals(obj.getMap().toString(), "{}", true);
	}
	
	@Test
	public void test_parse_from_null() throws JSONException {
		
		InputObject<String> obj = new ArgumentParser().parseWhere(null);
		JSONAssert.assertEquals(obj.getMap().toString(), "{}", true);
	}
	
	@Test
	public void test_parse_from_invalid_string() throws JSONException {
		
		InputObject<String> obj = new ArgumentParser().parseWhere("{lal}{ala,tral}ala}");
		JSONAssert.assertEquals(obj.getMap().toString(), "{}", true);
	}
	
}
