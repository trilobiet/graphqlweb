package com.trilobiet.graphqlweb.implementations.aexpgraphql;

import java.util.List;

import com.trilobiet.graphqlweb.datamodel.File;

import io.aexp.nodes.graphql.GraphQLRequestEntity;
import io.aexp.nodes.graphql.GraphQLResponseEntity;
import io.aexp.nodes.graphql.GraphQLTemplate;

/**
 * 
 * @author acdhirr
 *
 */
class FileResponse {
	
	static File getFile(GraphQLRequestEntity req) {
		
		GraphQLTemplate graphQLTemplate = new GraphQLTemplate();
		GraphQLResponseEntity<FileImp> responseEntity = graphQLTemplate.query(req, FileImp.class);
		return responseEntity.getResponse();
	}
	
	static List<File> getFiles(GraphQLRequestEntity req) {
		
		GraphQLTemplate graphQLTemplate = new GraphQLTemplate();
		GraphQLResponseEntity<FileList> responseEntity = graphQLTemplate.query(req, FileList.class);
		return responseEntity.getResponse().getFiles();
	}

}
