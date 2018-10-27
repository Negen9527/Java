package com.smt.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.smt.dao.CinemaDao;
import com.smt.dao.MovieDao;
import com.smt.dao.MovieHallDao;
import com.smt.dao.PlayDao;
import com.smt.entity.Cinema;

//����
public class PlaysModule {
	private static Integer cinameId = 0;
	private static Integer movieHallId = 0;
	private static PlayDao playDao = new PlayDao();
	private static CinemaDao cinemaDao = new CinemaDao();
	private static MovieHallDao movieHallDao = new MovieHallDao();
	private static MovieDao movieDao = new MovieDao();
	private static Scanner scanner = new Scanner(System.in);
	private static List<Cinema> cinemas = new ArrayList<>();
	//����ҳ��
	public static void showPlayMenu() {
		
	}
	
	
	//ѡ��ӰԺ
	public static void choiceCinema() {
		System.out.println("��ѡ��ӰԺ");
		cinemas = cinemaDao.selectAllCinema();
		Integer i = 1;
		for (Cinema cinema : cinemas) {
			System.out.print(i + ".");
			System.out.print(cinema.getName() + "--");
			System.out.print(cinema.getAddr() + "--");
			System.out.print(cinema.getCity() + "\n");
			i++;
		}
		System.out.println("0.����");
		System.out.print("��ѡ��:");
		Integer intChoice = scanner.nextInt();
	}
	
	
	
	public static void main(String[] args) {
		choiceCinema();
	}
}
