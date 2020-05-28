package com.trilobiet.graphqlweb.datamodel.comparator;

import java.util.Comparator;

import com.trilobiet.graphqlweb.datamodel.Topic;

public class TopicComparator implements NormalizedComparator<Topic> {

	@Override
	public int compare(Topic t1, Topic t2) {

		Comparator<Topic> c = Comparator.comparing(topic -> topic.getIndex());
		c = c.thenComparing( topic -> topic.getGroupHeader()!=null?topic.getGroupHeader():"" );
		c = c.thenComparing( topic -> normalized(topic.getName().trim() ) );

		return c.compare(t1, t2);
	}
}
