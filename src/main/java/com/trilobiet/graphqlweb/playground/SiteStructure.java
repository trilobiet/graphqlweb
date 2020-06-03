package com.trilobiet.graphqlweb.playground;

import java.util.Collection;
import java.util.List;

import com.trilobiet.graphqlweb.dao.DaoException;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.article.ArticleImp;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.article.ArticleList;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.article.GenericArticleDao;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.section.GenericSectionDao;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.section.SectionImp;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.section.SectionList;

public class SiteStructure {

	public static void main(String... strings) throws DaoException {
		
		GenericSectionDao<SectionImp> dao 
			= new GenericSectionDao<>("http://localhost:1337/graphql",SectionImp.class,SectionList.class);
		
		Collection<SectionImp> secs = dao.list();

		GenericArticleDao<ArticleImp> adao 
		= new GenericArticleDao<>("http://localhost:1337/graphql",ArticleImp.class,ArticleList.class);
		
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
				
				List<ArticleImp> articles;
				try {
					articles = adao.list(top, "index");
					articles.stream().forEach( art -> {
						System.out.println(ind + ind + ind + art.getTitle() + " (lang:" + art.getLanguage() + ")" );
					});
				} catch (DaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			});
		});
		
	}
}
