package com.smt.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtils {
    //����Connection����
	private static Connection connection = null;
    //����������
	static String driver = "com.mysql.jdbc.Driver";
    //URLָ��Ҫ���ʵ����ݿ���mydata
	static String url = "jdbc:mysql://localhost:3306/db_movie";
    //MySQL����ʱ���û���
	static String user = "root";
    //MySQL����ʱ������
	static String password = "123456";
    
    public static Connection getConn() {
    	try {
    		Class.forName(driver);
    		connection = DriverManager.getConnection(url, user, password);
    		if(connection!=null) {
//    			System.out.println("���ݿ����ӳɹ�");
    		}
    	} catch (Exception e) {
    		System.err.println("���ݿ�����ʧ��");
		}
    	
    	return connection;
    }
    
    
    public static void main(String[] args) {
		DbUtils dbUtils = new DbUtils();
		dbUtils.getConn();
	}
    
    }
