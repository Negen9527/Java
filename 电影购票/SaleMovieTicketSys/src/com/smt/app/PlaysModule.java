package com.smt.app;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.smt.dao.CinemaDao;
import com.smt.dao.MovieDao;
import com.smt.dao.MovieHallDao;
import com.smt.dao.PlayDao;
import com.smt.entity.Cinema;
import com.smt.entity.Movie;
import com.smt.entity.MovieHall;
import com.smt.entity.Play;

//����
public class PlaysModule {
	private static Integer cinemaId = 0;
	private static Integer movieHallId = 0;
	private static Integer movieId = 0;
	private static String cinemaName = "";
	private static String movieHallName = "";
	private static String movieName = "";
	
	private static Cinema currentCinema = new Cinema();
	private static PlayDao playDao = new PlayDao();
	private static CinemaDao cinemaDao = new CinemaDao();
	private static MovieHallDao movieHallDao = new MovieHallDao();
	private static MovieDao movieDao = new MovieDao();
	private static Scanner scanner = new Scanner(System.in);
	private static List<Cinema> cinemas = new ArrayList<>();
	
	
	//����ҳ��
	public static void showPlayMenu() {
		System.out.println("----------------------�����ι���----------------------");
		System.out.println("1.��ӳ���");
		System.out.println("2.�����б�");
		System.out.println("0.����");
		System.out.print("��ѡ��:");
		Integer intChoice = scanner.nextInt();
		switch (intChoice) {
		case 0:
			MainApp.adminMenu();
			break;
		case 1:
			choiceCinema();
			break;
		case 2:
			listPlay();
			break;
		default:
			break;
		}
		
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
		if(intChoice>=1) {
			currentCinema = cinemas.get(intChoice-1);
			cinemaId = currentCinema.getId();
			cinemaName = currentCinema.getName();
			System.out.print("��ǰӰԺ:");
			System.out.print(currentCinema.getName() + "--");
			System.out.print(currentCinema.getAddr() + "--");
			System.out.print(currentCinema.getCity() + "\n");
			choiceMovieHall(cinemaId);
		}else {
			showPlayMenu();
		}
		
	}
	
	
	//ѡ��Ӱ��
	public static void choiceMovieHall(Integer cinemaId) {
		System.out.println("��ѡ��Ӱ��");
		List<MovieHall> movieHalls = movieHallDao.selectAllMovieHall(cinemaId);
		Integer i = 1;
		for (MovieHall movieHall : movieHalls) {
			System.out.print(i + ".");
			System.out.println(movieHall.getName());
			i++;
		}
		System.out.println("0.����");
		System.out.print("��ѡ��:");
		Integer intChoice = scanner.nextInt();
		if(intChoice>=1) {
			MovieHall movieHall = movieHalls.get(intChoice-1);
			movieHallId = movieHall.getId();
			movieHallName = movieHall.getName();
			System.out.print("��ǰӰ��:");
			System.out.println(movieHall.getName());
			choiceMovie();
		}else {
			showPlayMenu();
		}
		
	}
	
	//ѡ���Ӱ
	public static void choiceMovie() {
		System.out.println("��ѡ���Ӱ");
		List<Movie> movies = movieDao.selectAllMovie();
		Integer i = 1;
		for (Movie movie : movies) {
			System.out.print(i + ".");
			System.out.println(movie.getName());
			i++;
		}
		
		System.out.println("0.����");
		System.out.print("��ѡ��:");
		Integer intChoice = scanner.nextInt();
		if(intChoice>=1) {
			Movie movie = movies.get(intChoice-1);
			movieId = movie.getId();
			movieName = movie.getName();
			System.out.print("��ǰ��Ӱ:");
			System.out.println(movie.getName());
			addPlay();
		}else {
			showPlayMenu();
		}
		
		
	}
	
	//��ӳ���
	public static void addPlay() {
		System.out.println("������Ҫ��ӵĳ���(��[15:00-17:00])");
	    String playTime = scanner.next();
	    System.out.println("������۸�");
	    Integer price = scanner.nextInt();
	    System.out.println("��ȷ��Ҫ��ӵĳ���");
	    System.out.print(movieName + "--");
	    System.out.print(cinemaName + "--");
	    System.out.print(movieHallName + "--");
	    System.out.println(playTime);
	    System.out.println("1.ȷ��");
	    System.out.println("0.ȡ��");
	    Integer choice = scanner.nextInt();
	    switch (choice) {
		case 1:
			Play play = new Play(movieId, playTime, price, movieHallId, cinemaId);
			Boolean blnResult = playDao.insertPlay(play);
			if(blnResult)
				System.out.println("��ӳɹ�");
			else
				System.out.println("���ʧ��");
			System.out.println("--------------------------------------------");
			String adminMenuStr =  "1.����\n";
			System.out.println(adminMenuStr);
			System.out.print("��ѡ��:");
			Integer intChoice = scanner.nextInt();
			switch (intChoice) {
			case 1:
				MainApp.adminMenu();
				break;
			default:
				break;
				}
			break;
		case 0:
			addPlay();
		default:
			break;
		}
	}
	
	//�鿴����
	public static void listPlay() {
		ResultSet rs= playDao.selectAllPlay();
		try {
			while (rs.next()) {
				System.out.print(rs.getString(1) + "--");
				System.out.print(rs.getString(2) + "--");
				System.out.print(rs.getString(3) + "--");
				System.out.println(rs.getString(4));
			}
			System.out.println("0.����");
			Integer choice = scanner.nextInt();
			if(0 == choice)
				showPlayMenu();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	
	public static void main(String[] args) {
		showPlayMenu();
	}
}
