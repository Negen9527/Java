package com.smt.entity;


//Ó°Ìü
public class MovieHall {
	private Integer id;
	private String name;
	private Integer hall_id;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getHall_id() {
		return hall_id;
	}
	public void setHall_id(Integer hall_id) {
		this.hall_id = hall_id;
	}
	
	
	public MovieHall() {

	}
	
	public MovieHall(String name, Integer hall_id) {
		super();
		this.name = name;
		this.hall_id = hall_id;
	}
	@Override
	public String toString() {
		return "movie_hall [id=" + id + ", name=" + name + ", hall_id=" + hall_id + "]";
	}
	
	
	
}
