package com.smt.app;

import java.util.Scanner;

public class MainApp {
	private static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		MainApp.showMenu();
	}
	
	
	/**
	 * 1.��������
	 */
	private static void showMenu() {
		System.out.println("----------------------����----------------------");
		String menuStr = "��ӭ������ﹺƱϵͳ\n"
						+ "1.��¼\n"
						+ "2.ע��\n"
						+ "3.��̨\n"
						+ "4.�˳�";
		System.out.println(menuStr);
		System.out.print("��ѡ��:");
//		Scanner scanner = new Scanner(System.in);
		int choice = scanner.nextInt();
		
		switch (choice) {
		case 1:
			//��½
			UserModule.userLogin();
			break;
		case 2:
			//ע��
			UserModule.userRegister();
			break;
		case 3:
			//��̨
			adminMenu();
			break;
		case 4:
			//�˳�
			System.exit(0);
			break;
		default:
			break;
		}
	}
	
	//��̨��ҳ
	public static void adminMenu() {
		System.out.println("----------------------����̨��ҳ��----------------------");
		String adminMenuStr =  "1.��Ӱ����\n"
							+ "2.ӰԺ����\n"
							+ "3.Ӱ������\n"
							+ "4.���ι���\n"
							+ "5.��������ӰƱ��\n\n"
							+ "6.�˳�";
		
		System.out.println(adminMenuStr);
		System.out.print("��ѡ��:");
		Scanner scanner = new Scanner(System.in);
		int choice = scanner.nextInt();
		while(true) {
			if(choice < 1 || choice > 6) {
				System.out.println("������1-6֮�������");
				System.out.println("������:");
				choice = scanner.nextInt();
			}else {
				break;
			}
		}
		switch (choice) {
		case 1:
			//��Ӱ����
			MovieModule.adminMovieMenu();
			break;
		case 2:
			//ӰԺ����
			CinemaModule.showCinemaMenu();
			break;
		case 3:
			//Ӱ������
			MovieHallModule.showMovieHallMenu();
			break;
		case 4:
			//���ι���
			PlaysModule.showPlayMenu();
			break;
		case 5:
			//��������ӰƱ��
			OrderModule.showAllOrders();
			break;
			
		case 6:
			//�˳�
			System.exit(0);
		default:
			break;
		}
		
		
	}
	
	

	
	
	
	
}
