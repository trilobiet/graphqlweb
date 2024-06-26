package com.trilobiet.graphqlweb.datamodel;

import java.time.OffsetDateTime;

/**
 * Core Article info (non recursive) 
 * @author acdhirr
 *
 */
public class ArticleOutline implements SortableArticle  {

	private String id, slug, title, summary, language;
	private int index;
	private boolean publish, topicLead;
	
	private OffsetDateTime created_at;
	private OffsetDateTime updated_at;
	private OffsetDateTime published_at;

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
	
	public OffsetDateTime getCreated_at() {
		return created_at;
	}
	
	public void setCreated_at(OffsetDateTime created_at) {
		this.created_at = created_at;
	}

	public OffsetDateTime getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(OffsetDateTime updated_at) {
		this.updated_at = updated_at;
	}

	public OffsetDateTime getPublished_at() {
		return published_at;
	}

	public void setPublished_at(OffsetDateTime published_at) {
		this.published_at = published_at;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		ArticleOutline other = (ArticleOutline) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ArticleOutline [slug=" + slug + ", title=" + title 
				+ ", language=" + language + ", index=" + index
				+ ", publish=" + publish + ", topicLead=" + topicLead + "]";
	}

}
