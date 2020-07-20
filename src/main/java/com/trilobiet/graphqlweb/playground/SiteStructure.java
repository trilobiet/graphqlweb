package com.trilobiet.graphqlweb.playground;

import java.util.Collection;

import com.trilobiet.graphqlweb.dao.DaoException;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.section.GenericSectionDao;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.section.SectionImp;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.section.SectionList;

class SiteStructure {

	public static void main(String... strings) throws DaoException {
		
		//String theserver = "http://localhost:1337/graphql";
		String theserver = "https://oapen-cms.trilobiet.eu/graphql";
		
		GenericSectionDao<SectionImp> dao 
			= new GenericSectionDao<>(theserver,SectionImp.class,SectionList.class);
		
		Collection<SectionImp> secs = dao.list();

		String ind = "          ";
		
		secs.stream().forEach( sec -> {
			
			System.out.println("\n\nsection: ");
			
			System.out.println( ind + sec.getName().toUpperCase() );
			System.out.println( ind + "translations: " + sec.getTranslations());
			
			System.out.println( ind + "topics: ");

			sec.getTopics().stream().forEach( top -> {
				
				System.out.println(ind + ind + top.getName());
				System.out.println(ind + ind + "topic translations: " + top.getTranslations());
				
				System.out.println(ind + ind +"articles: ");
				
				top.getArticles().stream().forEach( art -> {
					
					System.out.println(ind + ind + ind + art.getTitle() + " (lang:" + art.getLanguage() + ")" );
				});
				
			});
		});
		
	}
}
