package com.julianomengue.entity;

import java.util.ArrayList;
import java.util.List;

public class Game {

	private Cover cover;
	private String name;
	private String id;
	private String url;
	private String summary;
	private List<Screenshot> screenshots = new ArrayList<>();
	private List<Genre> genres = new ArrayList<>();
	private List<Platform> platforms = new ArrayList<>();
	private List<ReleaseDate> release_dates = new ArrayList<>();
	private List<Video> videos = new ArrayList<>();
	private List<Website> websites = new ArrayList<>();

	public Game() {
		super();
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

	public Cover getCover() {
		return cover;
	}

	public void setCover(Cover cover) {
		this.cover = cover;
	}

	public List<Screenshot> getScreenshots() {
		return screenshots;
	}

	public void setScreenshots(List<Screenshot> screenshots) {
		this.screenshots = screenshots;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	public List<Platform> getPlatforms() {
		return platforms;
	}

	public void setPlatforms(List<Platform> platforms) {
		this.platforms = platforms;
	}

	public List<ReleaseDate> getRelease_dates() {
		return release_dates;
	}

	public void setRelease_dates(List<ReleaseDate> release_dates) {
		this.release_dates = release_dates;
	}

	public List<Video> getVideos() {
		return videos;
	}

	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}

	public List<Website> getWebsites() {
		return websites;
	}

	public void setWebsites(List<Website> websites) {
		this.websites = websites;
	}

	@Override
	public String toString() {
		return "Game [cover=" + cover + ", name=" + name + ", id=" + id + ", url=" + url + ", summary=" + summary
				+ ", screenshots=" + screenshots + ", genres=" + genres + ", platforms=" + platforms
				+ ", release_dates=" + release_dates + ", videos=" + videos + ", websites=" + websites + "]";
	}

}
