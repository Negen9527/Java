package com.smt.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtils {
    //����Connection����
	private Connection connection = null;
    //����������
    String driver = "com.mysql.jdbc.Driver";
    //URLָ��Ҫ���ʵ����ݿ���mydata
    String url = "jdbc:mysql://localhost:3306/db_movie";
    //MySQL����ʱ���û���
    String user = "root";
    //MySQL����ʱ������
    String password = "123456";
    
    public Connection getConn() {
    	try {
    		getClass().forName(driver);
    		connection = DriverManager.getConnection(url, user, password);
    		if(connection!=null) {
    			System.out.println("���ݿ����ӳɹ�");
    		}
    	} catch (Exception e) {
    		System.out.println("���ݿ�����ʧ��");
		}
    	
    	return connection;
    }
    
    
    public static void main(String[] args) {
		DbUtils dbUtils = new DbUtils();
		dbUtils.getConn();
	}
    
    }
