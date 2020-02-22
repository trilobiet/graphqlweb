package com.trilobiet.graphqlweb.datamodel;

import java.util.List;

/**
 * 
 * @author acdhirr
 *
 */
public class Topic {

	private String id, name, description, slug, groupHeader;
	private String articleDisplay;
	private List<ArticleOutline> articles;
	private List<Translation> translations;
	private int index;
	private boolean hasMenuItem, publish;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}
	
	public String getSlug() {
		return slug;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Translation> getTranslations() {
		return translations;
	}

	public void setTranslations(List<Translation> translations) {
		this.translations = translations;
	}

	public List<ArticleOutline> getArticles() {
		return articles;
	}

	public void setArticles(List<ArticleOutline> articles) {
		this.articles = articles;
	}

	public String getGroupHeader() {
		return groupHeader;
	}

	public void setGroupHeader(String groupHeader) {
		this.groupHeader = groupHeader;
	}

	public boolean isHasMenuItem() {
		return hasMenuItem;
	}

	public void setHasMenuItem(boolean hasMenuItem) {
		this.hasMenuItem = hasMenuItem;
	}

	public boolean isPublish() {
		return publish;
	}

	public void setPublish(boolean publish) {
		this.publish = publish;
	}

	
	public String getArticleDisplay() {
		return articleDisplay;
	}

	public void setArticleDisplay(String articleDisplay) {
		this.articleDisplay = articleDisplay;
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
		Topic other = (Topic) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Topic " + name + ", articles=" + articles + ", index=" + index + "]";
	}

}
