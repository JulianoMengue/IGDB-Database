package com.julianomengue.entity;

public class Platform {

	private String id;
	private String name;

	public Platform() {
		super();
	}

	public Platform(String name) {
		super();
		this.name = name;
	}

	public Platform(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

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

}
