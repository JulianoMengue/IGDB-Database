package com.julianomengue.entity;

public class Year {

	private String id;
	private String year;

	public Year() {
		super();
	}

	public Year(String id, String year) {
		super();
		this.id = id;
		this.year = year;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

}
