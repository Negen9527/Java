package com.smt.app;

import java.util.Scanner;

import com.smt.dao.UserDao;
import com.smt.entity.User;

public class UserModule {
	private static UserDao userDao = new UserDao();
	private static Scanner scanner = new Scanner(System.in);
	
	//�û�ע��
	public static void userRegister() {
		System.out.println("-------------------ע��-----------------------");
		System.out.println("�������˺�:");
		String name = scanner.next();
		System.out.println("����������:");
		String password = scanner.next();
		System.out.println("���ٴ���������:");
		String passwordagain = scanner.next();
		if(!password.equals(passwordagain)) {
			System.out.println("�������벻һ�£�����������");
			userRegister();
		}
		
		System.out.println("�������Ա�(��orŮ):");
		Integer sex = scanner.next() == "��"?1:0;
		System.out.println("������绰:");
		String tel = scanner.next();
		
		User user = new User(name, password, sex, tel);
		boolean blnResult = userDao.insertUser(user);
		if(blnResult) {
			
			System.out.println("ע��ɹ�");
			System.out.println("1.��¼");
			System.out.println("0.�˳�");
			Integer intChoice = scanner.nextInt();
			switch (intChoice) {
			case 1:
				//��¼
//				userRegister();   
				break;
			case 0:
				System.exit(0);
				break;
			default:
				break;
			}
		}else {
			System.out.println("ע��ʧ��");
			System.out.println("1.����ע��");
			System.out.println("0.�˳�");
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
	
	//�û���¼
	public static void userLogin() {
		System.out.println("-------------------��¼-----------------------");
		System.out.println("�������˺�:");
		String name = scanner.next().trim();
		System.out.println("����������");
		String password = scanner.next().trim();
		User user = userDao.selectUserByNameAndPassword(name, password);
		if(null == user) {
			System.out.println("��¼ʧ��");
		}else {
			System.out.println("��¼�ɹ�");
		}
	}
	
}
