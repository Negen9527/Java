package com.smt.app;

import java.util.List;
import java.util.Scanner;

import com.smt.dao.CinemaDao;
import com.smt.entity.Cinema;
import com.smt.utils.InputUtils;

public class CinemaModule {
	
	private static CinemaDao cinemaDao = new CinemaDao();
	private static Scanner scanner = new Scanner(System.in);
	
	//ӰԺ�˵�
	public static void showCinemaMenu(){
		System.out.println("----------------------��ӰԺ����----------------------");
		String adminMenuStr =  "1.ӰԺ�б�\n"
							+ "2.���ӰԺ\n"
							+ "3.����\n";
		
		System.out.println(adminMenuStr);
		System.out.print("��ѡ��:");
		Scanner scanner = new Scanner(System.in);
//		int choice = scanner.nextInt();
		Integer choice = InputUtils.checkInputValue(1, 3);
		switch (choice) {
		case 1:
			//ӰԺ�б�
			listCinema();
			break;
		case 2:
			//���ӰԺ
			addCinema();
			break;
		case 3:
			//����
			MainApp.adminMenu();
			break;
		}
	}
	
	
	
	//���ӰԺ
	public static void addCinema() {
		System.out.println("----------------------���ӰԺ----------------------");
		System.out.println("������ӰԺ��:");
		String name = scanner.next();
		System.out.println("�������ַ:");
		String addr = scanner.next();
		System.out.println("���������:");
		String city = scanner.next();
		Cinema cinema = new Cinema(name, addr, city);
		Boolean blnResult = cinemaDao.insertCinema(cinema);
		if(blnResult)
			System.out.println("��ӳɹ�");
		else
			System.out.println("���ʧ��");
		System.out.println("--------------------------------------------");
		String adminMenuStr =  "1.�������\n"
							 + "2.����\n";
		System.out.println(adminMenuStr);
		System.out.print("��ѡ��:");
//		Integer intChoice = scanner.nextInt();
		Integer intChoice = InputUtils.checkInputValue(1, 2);
		switch (intChoice) {
		case 1:
			addCinema();
			break;
		case 2:
			showCinemaMenu();
			break;
		default:
			break;
		}
	
	}
	
	
	//ӰԺ�б�
	public static void listCinema() {
		List<Cinema> cinemas = cinemaDao.selectAllCinema();
		System.out.println("----------------------ӰԺ�б�----------------------");
		for (Cinema cinema : cinemas) {
			System.out.println("--------------------");
			System.out.println(cinema.getName());
			System.out.println("  " + cinema.getAddr());
			System.out.println("  " + cinema.getCity());
		}
		System.out.println("1.����");
		System.out.print("������:");
		Integer intChoice = scanner.nextInt();
		if(1 == intChoice) {
			showCinemaMenu();
		}
		
	}
	
	
	//����
	public static void main(String[] args) {
		showCinemaMenu();
	}
	
	
}
