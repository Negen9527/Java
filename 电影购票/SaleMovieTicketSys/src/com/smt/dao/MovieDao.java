package com.smt.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.smt.entity.Movie;
import com.smt.utils.DbUtils;

public class MovieDao {
	
	
	//添加电影
	public Boolean insertMovie(Movie movie) {
		String sql = "insert into movie(name,actors,duration,type,release_time)"
				+ " values(?,?,?,?,?)";
		Connection conn = DbUtils.getConn();
		PreparedStatement pstm;
		Boolean blnResult = false;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, movie.getName());
			pstm.setString(2, movie.getActors());
			pstm.setDouble(3, movie.getDuration());
			pstm.setString(4, movie.getType());
			pstm.setDate(5, new Date(movie.getReleaseTime().getTime()));
//			pstm.setInt(6, movie.getScore());
//			pstm.setString(7, movie.getSummary());
			
			Integer intResult = pstm.executeUpdate();
			if(1 == intResult)
				blnResult = true;
//			System.out.println(blnResult);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return blnResult;  
	}
	
	
	
	//所有电影
	public List<Movie> selectAllMovie(){
		String sql = "select * from movie";
		Connection conn = DbUtils.getConn();
		Statement stm = null;
		List<Movie> movies = null;
		try {
			stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			movies = new ArrayList<Movie>();
			while (rs.next()) {
				Movie temp = new Movie();
				temp.setId(rs.getInt("id"));
				temp.setName(rs.getString("name"));
				temp.setActors(rs.getString("actors"));
				temp.setDuration(rs.getDouble("duration"));
				temp.setType(rs.getString("type"));
				temp.setReleaseTime(rs.getDate("release_time"));
				temp.setScore(rs.getInt("score"));
				temp.setSummary(rs.getString("summary"));
				movies.add(temp);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movies;
	}
	
	
	//查询电影
	public List<Movie> searchMovieByName(String movieName){
		String sql = "select * from movie where name like %" + movieName + "%";
		Connection conn = DbUtils.getConn();
		Statement stm = null;
		List<Movie> movies = null;
		try {
			stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			movies = new ArrayList<Movie>();
			while (rs.next()) {
				Movie temp = new Movie();
				temp.setId(rs.getInt("id"));
				temp.setName(rs.getString("name"));
				temp.setActors(rs.getString("actors"));
				temp.setDuration(rs.getDouble("duration"));
				temp.setType(rs.getString("type"));
				temp.setReleaseTime(rs.getDate("release_time"));
				temp.setScore(rs.getInt("score"));
				temp.setSummary(rs.getString("summary"));
				movies.add(temp);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movies;
	}
	
	
	
//	public static void main(String[] args) {
//		MovieDao movieDao = new MovieDao();
//		movieDao.selectAllMovie();
//	}
	
}
