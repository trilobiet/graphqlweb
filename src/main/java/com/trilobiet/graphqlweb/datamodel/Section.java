package com.trilobiet.graphqlweb.datamodel;

import java.util.List;

public class Section {

	private String name;
	private List<Topic> topics; 

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Topic> getTopics() {
		return topics;
	}

	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}

	@Override
	public String toString() {
		return "Section [name=" + name + ", topics=" + topics + "]";
	}

	
}
