package com.demo.jzfp.entity;

public class Weather_data {
	private String date;

	private String dayPictureUrl;

	private String nightPictureUrl;

	private String weather;

	private String wind;

	private String temperature;

	public void setDate(String date) {
		this.date = date;
	}

	public String getDate() {
		return this.date;
	}

	public void setDayPictureUrl(String dayPictureUrl) {
		this.dayPictureUrl = dayPictureUrl;
	}

	public String getDayPictureUrl() {
		return this.dayPictureUrl;
	}

	public void setNightPictureUrl(String nightPictureUrl) {
		this.nightPictureUrl = nightPictureUrl;
	}

	public String getNightPictureUrl() {
		return this.nightPictureUrl;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getWeather() {
		return this.weather;
	}

	public void setWind(String wind) {
		this.wind = wind;
	}

	public String getWind() {
		return this.wind;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getTemperature() {
		return this.temperature;
	}

}