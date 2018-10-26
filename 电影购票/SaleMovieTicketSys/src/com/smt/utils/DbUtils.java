package com.smt.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtils {
    //声明Connection对象
	private Connection connection = null;
    //驱动程序名
    String driver = "com.mysql.jdbc.Driver";
    //URL指向要访问的数据库名mydata
    String url = "jdbc:mysql://localhost:3306/db_movie";
    //MySQL配置时的用户名
    String user = "root";
    //MySQL配置时的密码
    String password = "123456";
    
    public Connection getConn() {
    	try {
    		getClass().forName(driver);
    		connection = DriverManager.getConnection(url, user, password);
    		if(connection!=null) {
    			System.out.println("数据库连接成功");
    		}
    	} catch (Exception e) {
    		System.out.println("数据库连接失败");
		}
    	
    	return connection;
    }
    
    
    public static void main(String[] args) {
		DbUtils dbUtils = new DbUtils();
		dbUtils.getConn();
	}
    
    }
