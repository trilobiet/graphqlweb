package com.trilobiet.graphqlweb.datamodel;

/**
 * Core Article info (non recursive) 
 * @author acdhirr
 *
 */
public class ArticleOutline implements SortableArticle {

	private String id, slug, title, summary, language;
	private int index;
	private boolean publish, topicLead;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public boolean isTopicLead() {
		return topicLead;
	}

	public void setTopicLead(boolean topicLead) {
		this.topicLead = topicLead;
	}

	public boolean isPublish() {
		return publish;
	}

	public void setPublish(boolean publish) {
		this.publish = publish;
	}
	
	@Override
	public String toString() {
		return "ArticleOutline [index=" + index + ", slug=" + slug + ", title=" + title + "]";
	}


}
