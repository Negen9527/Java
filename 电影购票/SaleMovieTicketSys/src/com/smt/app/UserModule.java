package com.smt.app;

import java.util.Scanner;

import com.smt.dao.UserDao;
import com.smt.entity.User;

public class UserModule {
	private static UserDao userDao = new UserDao();
	private static Scanner scanner = new Scanner(System.in);
	
	//用户注册
	public static void userRegister() {
		System.out.println("-------------------注册-----------------------");
		System.out.println("请输入账号:");
		String name = scanner.next();
		System.out.println("请输入密码:");
		String password = scanner.next();
		System.out.println("请再次输入密码:");
		String passwordagain = scanner.next();
		if(!password.equals(passwordagain)) {
			System.out.println("两次密码不一致，请重新输入");
			userRegister();
		}
		
		System.out.println("请输入性别(男or女):");
		Integer sex = scanner.next() == "男"?1:0;
		System.out.println("请输入电话:");
		String tel = scanner.next();
		
		User user = new User(name, password, sex, tel);
		boolean blnResult = userDao.insertUser(user);
		if(blnResult) {
			
			System.out.println("注册成功");
			System.out.println("1.登录");
			System.out.println("0.退出");
			Integer intChoice = scanner.nextInt();
			switch (intChoice) {
			case 1:
				//登录
//				userRegister();   
				break;
			case 0:
				System.exit(0);
				break;
			default:
				break;
			}
		}else {
			System.out.println("注册失败");
			System.out.println("1.重新注册");
			System.out.println("0.退出");
			Integer intChoice = scanner.nextInt();
			switch (intChoice) {
			case 1:
				userRegister();
				break;
			case 0:
				System.exit(0);
				break;
			default:
				break;
			}
		}
	}
	
	//用户登录
	public static void userLogin() {
		System.out.println("-------------------登录-----------------------");
		System.out.println("请输入账号:");
		String name = scanner.next().trim();
		System.out.println("请输入密码");
		String password = scanner.next().trim();
		User user = userDao.selectUserByNameAndPassword(name, password);
		if(null == user) {
			System.out.println("登录失败");
		}else {
			System.out.println("登录成功");
		}
	}
	
}
