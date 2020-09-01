package com.trilobiet.graphqlweb.playground;

import java.util.List;

import com.trilobiet.graphqlweb.dao.DaoException;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.service.html.HtmlTopicService;
import com.trilobiet.graphqlweb.implementations.aexpgraphql2.topic.TopicImp;
import com.trilobiet.graphqlweb.markdown2html.Flexmark2HtmlFunction;
import com.trilobiet.graphqlweb.markdown2html.Md2HtmlConverter;
import com.trilobiet.graphqlweb.markdown2html.Md2HtmlTopicConverter;

public class TestTopic {

	public static void main(String[] args) throws DaoException {
		
		String theserver = "https://strapi.oapen.org/graphql";
		Md2HtmlConverter<TopicImp> conv = new Md2HtmlTopicConverter<>(new Flexmark2HtmlFunction());
		HtmlTopicService<TopicImp> ts = new HtmlTopicService<>(theserver, conv);
		
		List<TopicImp> res = null;
		res = ts.getByFieldValue("name", "TKBANNERTEST");
		System.out.println(res);
		
		res = ts.getByFieldValue("type", "topic");
		System.out.println(res);

		res = ts.getByFieldContainsValue("params", "topic=tooltest");
		System.out.println(res);
		
	}

}
