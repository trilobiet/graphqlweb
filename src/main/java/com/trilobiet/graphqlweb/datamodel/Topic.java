package com.trilobiet.graphqlweb.datamodel;

import java.util.List;

public class Topic {

	private String id, name;
	private List<Topic.Article> articles;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Topic.Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Topic.Article> articles) {
		this.articles = articles;
	}

	@Override
	public String toString() {
		return "Topic " + name + ", articles=" + articles + "]";
	}

	/* Non recursive minimal Article info */
	public static class Article {

		private String id, slug, title;

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

		@Override
		public String toString() {
			return "Topic.Article [id=" + id + ", slug=" + slug + ", title=" + title + "]";
		}
	}
	
}
