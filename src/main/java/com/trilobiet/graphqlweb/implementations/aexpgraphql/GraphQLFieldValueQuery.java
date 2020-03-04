package com.trilobiet.graphqlweb.implementations.aexpgraphql;

import com.trilobiet.graphqlweb.dao.FieldValueQuery;

public class GraphQLFieldValueQuery implements FieldValueQuery {
	
	private String field, value, sort;
	private MatchType matchtype;
	
	private GraphQLFieldValueQuery() {
		// Constructor is private: use the builder instead.
	}

	@Override
	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	@Override
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	@Override
	public MatchType getMatch() {
		return matchtype;
	}

	public void setMatch(MatchType matchtype) {
		this.matchtype = matchtype;
	}

	public static class Builder {
		
		private String field, value;
		private String sort;
		private MatchType matchtype = MatchType.EQUALS;
		
		public Builder(String field, String value) {
			this.field = field;
			this.value = value;
			this.sort = "id:ASC";
		}
		
		public Builder setSort(String sort) {
			this.sort = sort;
			return this;
		}

		public Builder setMatchType(MatchType mt) {
			this.matchtype = mt;
			return this;
		}
		
		public GraphQLFieldValueQuery build() {
			
			GraphQLFieldValueQuery q = new GraphQLFieldValueQuery();
			q.field = this.field;
			q.value = this.value;
			q.sort = this.sort;
			q.matchtype = this.matchtype;
			
			return q;
		}
		
	}
	
}
