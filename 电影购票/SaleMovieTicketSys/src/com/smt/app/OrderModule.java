package com.smt.app;

import java.util.List;
import java.util.Scanner;

import com.smt.dao.OrderDao;
import com.smt.entity.Order;

//ӆ��
public class OrderModule {
	private static Scanner scanner = new Scanner(System.in);
	private static OrderDao orderDao = new OrderDao();
	
	
	//����ӆ��
	public static void showAllOrders() {
		List<Order> orders = orderDao.selectAllOrder();
		Integer i = 1;
		System.out.println("------------------����ӆ��-------------");
		for (Order order : orders) {
			System.out.print(i + ".");
			System.out.print(order.getUserName() + "  ");
			System.out.print(order.getMovieName() + "  ");
			System.out.print(order.getCinemaName() + "  ");
			System.out.print(order.getHallName() + "  ");
			System.out.print(order.getPlayTime() + "  ");
			System.out.print(order.getSeat()+ "̖  ");
			System.out.println(order.getStatus()==1?"��֧��":"δ֧��");
			i++;
		}
		
		System.out.println("0.����");
		if(0 == scanner.nextInt()) {
			MainApp.adminMenu();
		}
	}
}
