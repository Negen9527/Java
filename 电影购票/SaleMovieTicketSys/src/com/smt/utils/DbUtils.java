package com.smt.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class DbUtils {
    //声明Connection对象
	private static Connection connection = null;
    //驱动程序名
	static String driver = "com.mysql.jdbc.Driver";
    //URL指向要访问的数据库名mydata
	static String url = "jdbc:mysql://localhost:3306/db_movie";
    //MySQL配置时的用户名
	static String user = "root";
    //MySQL配置时的密码
	static String password = "123456";
    
    public static Connection getConn() {
    	try {
    		Class.forName(driver);
    		connection = DriverManager.getConnection(url, user, password);
    		if(connection!=null) {
//    			System.out.println("数据库连接成功");
    		}
    	} catch (Exception e) {
    		System.err.println("数据库连接失败");
		}
    	
    	return connection;
    }
    

    
    //关闭连接
    // Connection ,Statement, ResultSet 这几个资源的关闭是有顺序的
    public static void close (Object...objects) {
        Map<String,Object> map = new HashMap();
        for(Object o : objects){
            if(o instanceof ResultSet){
                map.put("ResultSet",o);
            }else if(o instanceof Connection){
                map.put("Connection",o);
            }else if(o instanceof Statement){
                map.put("Statement",o);
            }else if(o instanceof PreparedStatement){
                map.put("PreparedStatement",o);
            }else{
                System.out.println("关闭失败 ");
            }
        }
        Object obj = map.get("ResultSet");
        if(obj!=null){
            ResultSet r = (ResultSet)obj;
            try {
                r.close();
                map.remove("ResultSet");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        obj = map.get("PreparedStatement");
        if(obj!=null){
            PreparedStatement p = (PreparedStatement)obj;
            try {
                p.close();
                map.remove("PreparedStatement");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        obj = map.get("Statement");
        if(obj!=null){
            Statement s = (Statement)obj;
            try {
                s.close();
                map.remove("Statement");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        obj = map.get("Connection");
        if(obj!=null){
            Connection c = (Connection)obj;
            try{
                c.close();
                map.remove("Connection");
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
}
    
    
    
    
//    public static void main(String[] args) {
//		DbUtils dbUtils = new DbUtils();
//		dbUtils.getConn();
//	}
    
    
