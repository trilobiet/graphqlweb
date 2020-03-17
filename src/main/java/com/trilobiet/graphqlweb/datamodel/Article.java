package com.trilobiet.graphqlweb.datamodel;

import java.util.List;

/**
 * 
 * @author acdhirr
 *
 */
public class Article extends ArticleOutline {

	private String content, tags, cssClass, params, externalLink;
	private List<Category> categories;
	private boolean hasMenuItem, spotlight;
	private ArticleOutline prevArticle, nextArticle;
	private List<File> media;
	private List<Snippet> snippets;
	
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
	
	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
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

	public List<File> getMedia() {
		return media;
	}

	public void setMedia(List<File> media) {
		this.media = media;
	}
	
	public List<Snippet> getSnippets() {
		return snippets;
	}

	public void setSnippets(List<Snippet> snippets) {
		this.snippets = snippets;
	}

}
