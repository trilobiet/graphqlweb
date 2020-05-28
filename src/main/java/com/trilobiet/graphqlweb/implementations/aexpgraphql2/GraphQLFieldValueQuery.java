package com.trilobiet.graphqlweb.implementations.aexpgraphql2;

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

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public String getSort() {
		return sort;
	}

	@Override
	public MatchType getMatch() {
		return matchtype;
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((field == null) ? 0 : field.hashCode());
		result = prime * result + ((matchtype == null) ? 0 : matchtype.hashCode());
		result = prime * result + ((sort == null) ? 0 : sort.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GraphQLFieldValueQuery other = (GraphQLFieldValueQuery) obj;
		if (field == null) {
			if (other.field != null)
				return false;
		} else if (!field.equals(other.field))
			return false;
		if (matchtype != other.matchtype)
			return false;
		if (sort == null) {
			if (other.sort != null)
				return false;
		} else if (!sort.equals(other.sort))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GraphQLFieldValueQuery [field=" + field + ", value=" + value + ", sort=" + sort + ", matchtype="
				+ matchtype + "]";
	}
	
}
