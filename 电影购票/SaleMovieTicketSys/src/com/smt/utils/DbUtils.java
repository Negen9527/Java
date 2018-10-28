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
    

    
    //�ر�����
    // Connection ,Statement, ResultSet �⼸����Դ�Ĺر�����˳���
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
                System.out.println("�ر�ʧ�� ");
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
    
    
