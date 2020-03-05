package com.trilobiet.graphqlweb.datamodel;

import java.util.List;

/**
 * 
 * @author acdhirr
 *
 */
public class Article extends ArticleOutline {

	private String content, tags, cssClass, externalLink;
	private List<Category> categories;
	private int index;
	private boolean hasMenuItem, spotlight;
	private ArticleOutline prevArticle, nextArticle;
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	public String getExternalLink() {
		return externalLink;
	}

	public void setExternalLink(String externalLink) {
		this.externalLink = externalLink;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
	public boolean isHasMenuItem() {
		return hasMenuItem;
	}

	public void setHasMenuItem(boolean hasMenuItem) {
		this.hasMenuItem = hasMenuItem;
	}

	public boolean isSpotlight() {
		return spotlight;
	}

	public void setSpotlight(boolean spotlight) {
		this.spotlight = spotlight;
	}

	public ArticleOutline getPrevArticle() {
		return prevArticle;
	}

	public void setPrevArticle(ArticleOutline prevArticle) {
		this.prevArticle = prevArticle;
	}

	public ArticleOutline getNextArticle() {
		return nextArticle;
	}

	public void setNextArticle(ArticleOutline nextArticle) {
		this.nextArticle = nextArticle;
	}
	
	@Override
	public String toString() {
		return "Article [index=" + index + ", slug=" + getSlug() 
				+ ", language=" + getLanguage() 
				+ " prev=" + prevArticle + " next=" + nextArticle 
				+ "]";
	}
	
}
