package com.smt.app;

import java.util.Scanner;

import com.smt.dao.UserDao;
import com.smt.entity.MovieTicket;
import com.smt.entity.User;

public class UserModule {
	private static UserDao userDao = new UserDao();
	private static Scanner scanner = new Scanner(System.in);
	public static Integer userId;
	public static User currentUser = new User();
	public static Double currentBalance;
	
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
				userLogin();
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
			userId = user.getId();
			currentUser = user;
			currentBalance = user.getBalance();
			showOnlineMainMenu();
		}
	}
	
	
	//登录后的首页
	public static void showOnlineMainMenu() {
		System.out.println("-------------------登录成功【首页】-----------------------");
		System.out.println("1.购票");
		System.out.println("2.电影查询");
		System.out.println("3.充值");
		System.out.println("4.我的电影票【评论】");
		System.out.println("5.个人信息");
		System.out.println("6.退出");
		System.out.print("请选择:");
		Integer intChoice = scanner.nextInt();
		switch (intChoice) {
		case 1:
			//购票
			MovieTicketModule.listMovie();
			break;
		case 2:
			//电影查询
			
			break;
		case 3:
			//充值
			MovieTicketModule.recharge();
			break;
		case 4:
			//我的电影票【评论】
			
			break;
		case 5:
			//个人信息
			
			break;
		case 6:
			//退出
			System.exit(0);
			break;
		default:
			break;
		}
		
		
	}
	
}
