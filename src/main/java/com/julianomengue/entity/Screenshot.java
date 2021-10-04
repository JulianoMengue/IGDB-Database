package com.julianomengue.entity;

public class Screenshot {

	private String id;
	private String url;

	public Screenshot() {
		super();
	}

	public Screenshot(String url) {
		super();
		this.url = url;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
