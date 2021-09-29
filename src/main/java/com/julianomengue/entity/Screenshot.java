package com.julianomengue.entity;

public class Screenshot {

	private String id;
	private String game;
	private int height;
	private String image_id;
	private String url;
	private int width;

	public Screenshot() {
		super();
	}

	public Screenshot(String id, String game, int height, String image_id, String url, int width) {
		super();
		this.id = id;
		this.game = game;
		this.height = height;
		this.image_id = image_id;
		this.url = url;
		this.width = width;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getImage_id() {
		return image_id;
	}

	public void setImage_id(String image_id) {
		this.image_id = image_id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

}
