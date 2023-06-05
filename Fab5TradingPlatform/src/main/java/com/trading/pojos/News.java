package com.trading.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "news")
public class News {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 7000)
	private String title;
	
	@Column(length = 7000)
	private String description;
	
	@Column(length = 1000)
	private String url;
	
	@Column(length = 100)
	private String publishTime;
	
	public News() {
		// TODO Auto-generated constructor stub
	}

	public News(String title, String description, String url, String publishTime) {
		super();
		this.title = title;
		this.description = description;
		this.url = url;
		this.publishTime = publishTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	@Override
	public String toString() {
		return "News [id=" + id + ", title=" + title + ", description=" + description + ", url=" + url
				+ ", publishTime=" + publishTime + "]";
	}
	
	

}
