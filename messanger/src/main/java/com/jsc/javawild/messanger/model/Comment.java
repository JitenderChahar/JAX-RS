package com.jsc.javawild.messanger.model;

import java.util.Date;

public class Comment {
	private long id;
	private String author;
	private String message;
	private Date created;

	public Comment() {
	}

	public Comment(long id, String author, String message) {
		this.id = id;
		this.author = author;
		this.message = message;
		this.created = new Date();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}
