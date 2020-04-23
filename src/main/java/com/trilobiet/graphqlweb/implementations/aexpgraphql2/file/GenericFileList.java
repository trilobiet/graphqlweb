package com.trilobiet.graphqlweb.implementations.aexpgraphql2.file;

import java.util.List;

import com.trilobiet.graphqlweb.datamodel.File;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.GraphQLMappable;

/**
 * 
 * @author acdhirr
 *
 */
public interface GenericFileList<T extends File> extends GraphQLMappable {

	public List<T> getFiles();
	public void setFiles(List<T> files);
}