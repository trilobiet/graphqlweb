package com.trilobiet.graphqlweb.implementations.aexpgraphql;

import java.util.Arrays;

import io.aexp.nodes.graphql.InputObject;
import io.aexp.nodes.graphql.InputObject.Builder;

/**
 * 
 * @author acdhirr
 *
 */
public class ArgumentParser {
	
	public InputObject<String> parseWhere(String where) {
		
		if (where == null ) where = "";
		where = where.replaceAll("[{}]" , "");
		
		Builder<String> args = new InputObject.Builder<>();
		
		Arrays.asList(where.split(",")).stream()
			.forEach( kv -> {
				String[] pair = kv.split(":");
				if(pair.length == 2) args.put(pair[0], pair[1]);
			});
		
		return args.build();
	}
	
}
