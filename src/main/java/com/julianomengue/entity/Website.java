package com.julianomengue.entity;

public class Website {

	private String url;
	private String id;

	public Website() {
		super();
	}

	public Website(String url, String id) {
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
