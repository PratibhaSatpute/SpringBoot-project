package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class CategoryModel
{
	 public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public boolean isPublished() {
		return published;
	}
	public void setPublished(boolean published) {
		this.published = published;
	}
	@Id
	  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_generator")

private long id;
public CategoryModel() {
		super();
	}
public CategoryModel( String title, String description, boolean published) {
		super();
		
		this.title = title;
		this.description = description;
		this.published = published;
	}
@Column(name = "title")

private String title;
@Column(name = "description")
private String description;
@Column(name = "published")
private boolean published;
	
}
