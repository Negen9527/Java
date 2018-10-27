package com.smt.entity;

import java.sql.Time;

public class Play {
	private Integer id;
	private Integer movieId;
	private Time playTime;
	private Integer price;
	private Integer hallId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMovieId() {
		return movieId;
	}
	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}
	public Time getPlayTime() {
		return playTime;
	}
	public void setPlayTime(Time playTime) {
		this.playTime = playTime;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getHallId() {
		return hallId;
	}
	public void setHallId(Integer hallId) {
		this.hallId = hallId;
	}
	public Play(Integer movieId, Time playTime, Integer price, Integer hallId) {
		super();
		this.movieId = movieId;
		this.playTime = playTime;
		this.price = price;
		this.hallId = hallId;
	}
	public Play() {
		super();
	}
	@Override
	public String toString() {
		return "Play [id=" + id + ", movieId=" + movieId + ", playTime=" + playTime + ", price=" + price + ", hallId="
				+ hallId + "]";
	}
	
	
	
	
}
