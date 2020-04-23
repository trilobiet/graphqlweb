package com.trilobiet.graphqlweb.implementations.aexpgraphql2.file;

import java.util.List;

import io.aexp.nodes.graphql.annotations.GraphQLArgument;
import io.aexp.nodes.graphql.annotations.GraphQLArguments;

/**
 * 
 * @author acdhirr
 *
 */
public class FileList implements GenericFileList<FileImp> {
	
	@GraphQLArguments({
		@GraphQLArgument(
			name = "sort",
			type = "String",
			optional = true, value = "name:asc,size:asc"
		),
		@GraphQLArgument(
			name = "where",
			optional = true
		)
	})
	
	private List<FileImp> files;

	public List<FileImp> getFiles() {
		return files;
	}

	public void setFiles(List<FileImp> files) {
		this.files = files;
	}
}
