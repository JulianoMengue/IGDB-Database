package com.julianomengue.entity;

public class Cover {

	private String url;
	private String id;

	public Cover() {
		super();
	}

	public Cover(String url, String id) {
		super();
		this.url = url;
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
