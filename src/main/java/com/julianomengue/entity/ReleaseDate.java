package com.julianomengue.entity;

public class ReleaseDate {

	private String id;
	private String human;
	private Long date;
	private Platform platform;
	private Region region;

	public ReleaseDate() {
		super();
	}

	public ReleaseDate(String human) {
		super();
		this.human = human;
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

	public Platform getPlatform() {
		return platform;
	}

	public void setPlatform(Platform platform) {
		this.platform = platform;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

}
