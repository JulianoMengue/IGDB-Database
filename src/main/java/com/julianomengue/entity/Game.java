package com.julianomengue.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "games")
public class Game {

	private String cover;
	private String name;
	private String id;
	private String url;
	private String summary;
	private List<String> screenshots = new ArrayList<>();
	private List<String> genres = new ArrayList<>();
	private List<String> platforms = new ArrayList<>();
	private List<String> release_dates = new ArrayList<>();
	private List<String> videos = new ArrayList<>();
	private List<String> websites = new ArrayList<>();

	public Game() {
		super();
	}

	public Game(String cover, String id) {
		super();
		this.cover = cover;
		this.id = id;
	}

	public Game(String cover, String name, String id, String url) {
		super();
		this.cover = cover;
		this.name = name;
		this.id = id;
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public List<String> getGenres() {
		return genres;
	}

	public void setGenres(List<String> genres) {
		this.genres = genres;
	}

	public List<String> getPlatforms() {
		return platforms;
	}

	public void setPlatforms(List<String> platforms) {
		this.platforms = platforms;
	}

	public List<String> getRelease_dates() {
		return release_dates;
	}

	public void setRelease_dates(List<String> release_dates) {
		this.release_dates = release_dates;
	}

	public List<String> getVideos() {
		return videos;
	}

	public void setVideos(List<String> videos) {
		this.videos = videos;
	}

	public List<String> getWebsites() {
		return websites;
	}

	public void setWebsites(List<String> websites) {
		this.websites = websites;
	}

	public List<String> getScreenshots() {
		return screenshots;
	}

	public void setScreenshots(List<String> screenshots) {
		this.screenshots = screenshots;
	}

}
