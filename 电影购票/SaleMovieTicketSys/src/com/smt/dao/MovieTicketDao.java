package com.smt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.smt.entity.MovieTicket;
import com.smt.utils.DbUtils;

//购买电影票
public class MovieTicketDao {

	//添加订单
	public Boolean insertMovieTicket(MovieTicket movieTicket) {
		String sql = "insert into movie_ticket(seat_id,play_id,user_id,pay_price)"
				+ " values(?,?,?,?)";
		Connection conn = DbUtils.getConn();
		PreparedStatement pstm;
		Boolean blnResult = false;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, movieTicket.getSeatId());
			pstm.setInt(2, movieTicket.getPlayId());
			pstm.setInt(3, movieTicket.getUserId());
			pstm.setInt(4, movieTicket.getPayPrice());
			Integer intResult = pstm.executeUpdate();
			if(1 == intResult)
				blnResult = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return blnResult; 
	}
	
	
	//查看已选座位  通过场次id
	public List<Integer> selectAllSeatByPlayId(Integer playId){
		List<Integer> seats = new ArrayList<Integer>();
		String sql = "select seat_id from movie_ticket where play_id=" + playId;
		
		Connection conn = DbUtils.getConn();
		Statement stm = null;
		ResultSet rs = null;
			try {
				stm = conn.createStatement();
				 rs = stm.executeQuery(sql);
				 while (rs.next()) {
					 seats.add(rs.getInt("seat_id"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return seats;
		
	}
	
	
	
	
	
	
}
