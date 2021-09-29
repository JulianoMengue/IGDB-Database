package com.julianomengue.entity;

public class ReleaseDate {

	private String id;
	private String human;
	private String platform;

	public ReleaseDate() {
		super();
	}

	public ReleaseDate(String id, String human, String platform) {
		super();
		this.id = id;
		this.human = human;
		this.platform = platform;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHuman() {
		return human;
	}

	public void setHuman(String human) {
		this.human = human;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

}
