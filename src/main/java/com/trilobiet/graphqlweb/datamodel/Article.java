package com.trilobiet.graphqlweb.datamodel;

import java.time.LocalDate;
import java.util.List;

/**
 * 
 * @author acdhirr
 *
 */
public class Article implements SortableArticle {

	private String id, slug, title, summary, content, tags, 
			language, cssClass, type, menuLabel, externalLink;
	private List<Category> categories;
	private int index;
	private boolean hasMenuItem, publish, spotlight;
	private LocalDate publishFrom, publishUntil;
	private ArticleOutline prevArticle, nextArticle;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public int getIndex() {
		return index;
	}
	
	public void setIndex(int index) {
		this.index = index;
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

	public String getLanguage() {
		return tags;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	
	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMenuLabel() {
		return menuLabel;
	}

	public void setMenuLabel(String menuLabel) {
		this.menuLabel = menuLabel;
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

	public boolean isPublish() {
		return publish;
	}

	public void setPublish(boolean publish) {
		this.publish = publish;
	}

	public LocalDate getPublishFrom() {
		return publishFrom;
	}

	public void setPublishFrom(LocalDate publishFrom) {
		this.publishFrom = publishFrom;
	}

	public LocalDate getPublishUntil() {
		return publishUntil;
	}

	public void setPublishUntil(LocalDate publishUntil) {
		this.publishUntil = publishUntil;
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

	public static class PrevNextArticle {
		
		String title, language, slug;
		int index;
		
		public String getTitle() {
			return title;
		}
		
		public void setTitle(String title) {
			this.title = title;
		}
		
		public String getLanguage() {
			return language;
		}
		
		public void setLanguage(String language) {
			this.language = language;
		}
		
		public String getSlug() {
			return slug;
		}
		
		public void setSlug(String slug) {
			this.slug = slug;
		}
		
		public int getIndex() {
			return index;
		}
		
		public void setIndex(int index) {
			this.index = index;
		}

		public String toString() {
			return "PrevNextArticle [slug=" + slug + "]";
		}
		
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
		Article other = (Article) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Article [index=" + index + ", slug=" + slug 
				+ ", language=" + language 
				+ " prev=" + prevArticle + " next=" + nextArticle 
				+ "]";
	}
	
}
