package org.sivaiitroracle9.spring.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

@Entity
public class Item {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Size(min=1, message="Item name must be at least 1 character!")
	private String item;
	
	@Size(min=1, message="Title name must be at least 1 character!")
	private String title;
	
	@Size(min=1, message="Description name must be at least 1 character!")
	private String description;
	
	@Column(name="published_date")
	private Date publishedDate;
	
	@ManyToOne
	@JoinColumn(name="blog_id")
	private Blog blog;
	
	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	private String link;

}
