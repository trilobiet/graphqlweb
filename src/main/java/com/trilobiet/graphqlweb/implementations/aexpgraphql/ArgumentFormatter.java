package com.trilobiet.graphqlweb.implementations.aexpgraphql;

import java.util.Map;
import java.util.stream.Collectors;

import io.aexp.nodes.graphql.InputObject;
import io.aexp.nodes.graphql.InputObject.Builder;

public class ArgumentFormatter {
	
	public InputObject<String> getWhereArgument(Map<String, String> filterpairs) {

		Builder<String> args = new InputObject.Builder<>();
		filterpairs.forEach( (k,v) -> args.put(k, v));
		return args.build();
	}

	public String getSortArgument(Map<String, String> sortfields) {

		String args = sortfields.entrySet().stream()
				.map( kv -> kv.getKey() + ":" + kv.getValue() )
				.collect(Collectors.joining(","));
		
		return args;
	}
	
}
