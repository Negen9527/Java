package com.smt.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.smt.dao.CinemaDao;
import com.smt.dao.MovieDao;
import com.smt.dao.MovieHallDao;
import com.smt.dao.MovieTicketDao;
import com.smt.dao.PlayDao;
import com.smt.dao.UserDao;
import com.smt.entity.Cinema;
import com.smt.entity.Movie;
import com.smt.entity.MovieTicket;
import com.smt.entity.PlayAndHall;
import com.smt.entity.User;
import com.smt.entity.UserTicket;

//��ӰƱģ�� 
public class MovieTicketModule {
	private static Scanner scanner = new Scanner(System.in);
	private static MovieTicketDao movieTicketDao = new MovieTicketDao();
	private static MovieDao movieDao = new MovieDao();
	private static CinemaDao cinemaDao = new CinemaDao();
	private static MovieHallDao movieHallDao = new MovieHallDao();
	private static PlayDao playDao = new PlayDao();
	private static UserDao userDao = new UserDao();
	
	private static Integer currentPage = 1;
	private static Map<Integer, List<Movie>> pageTeam;
	
	private static Integer movieId = 0;
	private static Integer cinemaId = 0;
	private static Integer playId = 0;
	
	private static String movieName = "";
	private static String cinemaName = "";
	private static String movieHallName = "";
	private static String playTime = "";
	private static Integer seat = 0;
	private static Integer price = 0;
	
	public static List<Movie> movieList = new ArrayList<>();
	public static Integer index;
	
	//��Ӱ�б�
	public static void listMovie() {
		List<Movie> movies = movieDao.selectAllMovie();
//		Map<Integer, List<Movie>> pageTeam = new HashMap<>();     //��ҳ
		pageTeam = new HashMap<>();
		int page = 1;
		List<Movie> tempList = new ArrayList<Movie>();
		for(int i=0;i<movies.size();i++) {
			tempList.add(movies.get(i));
			if((i+1)%5==0) {
				pageTeam.put(page, tempList);
				page++;
				tempList = new ArrayList<Movie>();
			}
		}
		pageTeam.put(page, tempList);	
		
		purchaseTicket(pageTeam);	
		
	}
	
	
	
	//��Ʊ
	private static void purchaseTicket(Map<Integer, List<Movie>> pageTeam) {
		Integer totalPage = pageTeam.size();
		System.out.println("-------------------��Ӱ-----------------------");
		System.out.println("��ѡ���Ӱ:");
		try {
			for(int i=0;i<5;i++) {
				System.out.println(i+1 + ":" + pageTeam.get(currentPage).get(i).getName());
			}
		} catch (Exception e) {
		}
		
		String adminMenuStr =  "\n7.��һҳ\n"
							+ "8.��һҳ\n"
							+ "9.����\n";
		System.out.println(adminMenuStr);
		System.out.print("��ѡ��:");
		int choice = scanner.nextInt();
		if(choice > 0 && choice < 6) {
			movieList = pageTeam.get(currentPage);
			index = choice;
			showMovieInfo(pageTeam.get(currentPage), choice);
		}
		
		switch (choice) {
		case 7:
			//��һҳ
			currentPage --;
			if(currentPage < 1) {
				System.out.println("û����һҳ");
				currentPage ++;
				purchaseTicket(pageTeam);
			}else {
				purchaseTicket(pageTeam);
			}
			break;
		case 8:
			//��һҳ
			currentPage ++;
			if(currentPage > totalPage) {
				System.out.println("û����һҳ");
				currentPage--;
				purchaseTicket(pageTeam);
			}else {
				purchaseTicket(pageTeam);
			}
			break;
		case 9:
			//����
			UserModule.showOnlineMainMenu();
			break;
		default:
			break;
		}
	}	
	
	
	//��ʾ��Ӱ��ϸ��Ϣ
	public static void showMovieInfo(List<Movie> movieList,Integer index) {
		System.out.println("----------------------��Ӱ����----------------------");
		Movie movie = movieList.get(index-1); 
		System.out.println(movie.getName());
		System.out.println("��Ա:" + movie.getActors());
		System.out.println("ʱ��:" + movie.getDuration() + "����");
		System.out.println("����:" + movie.getType());
		System.out.println("��ӳʱ��:" + movie.getReleaseTime());
		System.out.println("����:" + ((movie.getScore()==0)?"��������":movie.getScore()));
		
		System.out.println("\n1.��Ʊ");
		System.out.println("2.�鿴����");
		System.out.println("0.����");
		Integer intChoice = scanner.nextInt();
		switch (intChoice) {
		case 1:
			//��Ʊ
			movieId = movie.getId();
			movieName = movie.getName();
			choiceCinemaByMovieId(movieId);
			break;
		case 2:
			//�鿴����
			movieId = movie.getId();
			movieName = movie.getName();
			System.out.println(movieName);
			CommentModule.listAllComment(movieId);
			break;
		case 0:
			
			purchaseTicket(pageTeam);
			currentPage = 1;
			break;
		default:
			break;
		}
	}
	
	
	//ѡ��ӰԺ
	public static void choiceCinemaByMovieId(Integer movieId) {
		List<Cinema> cinemas = cinemaDao.selectCinemaByMovieId(movieId);
		
		Integer i = 1; 
		for (Cinema cinema : cinemas) {
			System.out.print(i + ".");
			System.out.println(cinema.getName());
			System.out.println("  " + cinema.getAddr());
			i++;
		}
		
		System.out.println("0.����");
		System.out.print("��ѡ��:");
		Integer intChoice = scanner.nextInt();
		if(intChoice>=1) {
			Cinema currentCinema = cinemas.get(intChoice-1);
			cinemaId = currentCinema.getId();
			cinemaName = currentCinema.getName();
			System.out.print("��ǰӰԺ:");
			System.out.print(currentCinema.getName() + "--");
			System.out.print(currentCinema.getAddr() + "--");
			System.out.print(currentCinema.getCity() + "\n");
			//ѡ�񳡴�
			choicePlayByMovieIdAndCinemaId();
		}else {
			listMovie();
		}
		
	}
	
	
	
	//ѡ�񳡴�
	public static void choicePlayByMovieIdAndCinemaId() {
		List<PlayAndHall> playAndHalls = playDao.selectPlayAndHallNameByMovieIdAndCinemaId(movieId, cinemaId);
		System.out.println("-------------------����ѡ��-----------------------");
		Integer i = 1;
		for (PlayAndHall playAndHall : playAndHalls) {
			System.out.print(i + ".");
			System.out.print(playAndHall.getName() + "---");
			System.out.print(playAndHall.getPlayTime() + "---");
			System.out.print(playAndHall.getPrice() + " RMB");
			i++;
		}
		
		System.out.println("\n0.����");
		System.out.print("��ѡ��:");
		Integer intChoice = scanner.nextInt();
		if(intChoice>=1) {
			PlayAndHall playAndHall = playAndHalls.get(intChoice-1);
			playId = playAndHall.getId();
			movieHallName = playAndHall.getName();
			playTime = playAndHall.getPlayTime();
			price = playAndHall.getPrice();
			//��λ
			choiceSeat();
		}else {
			
			choiceCinemaByMovieId(movieId);
		}
		
		
		
	}
	
	
	//ѡ����λ
	public static void choiceSeat() {
		List<Integer> selectedSeats = movieTicketDao.selectAllSeatByPlayId(playId); 
		System.out.println("-------------------��λѡ��-----------------------");
		System.out.println("        ===========����Ļ===========");
		for(int i = 1 ; i< 10; i++) {
			System.out.print(" ");
			for(int j = 1; j < 8; j++) {
				System.out.print(" ");
				if(selectedSeats.contains(i*10 + j)) {
					System.out.print("[xx] ");
				}else {
					
					System.out.print("[" + i + j + "] ");
				}
			}
			System.out.println();
		}
		
		
		System.out.println("\nֱ��������λ��ѡ���\n0.����");
		System.out.println("\n��ѡ��:");
		Integer intChoice = scanner.nextInt();
		if(0 == intChoice) {
			
			choicePlayByMovieIdAndCinemaId();
		}else {
			//���ɵ�ӰƱ
			seat = intChoice;
			genMovieTicket();
		}
		
	}
	
	//���ɵ�ӰƱ
	public static void genMovieTicket() {
		System.out.println("-------------------��ӰƱ-----------------------");
		System.out.println("��Ӱ:" + movieName);
		System.out.println("ӰԺ:" + cinemaName);
		System.out.println("Ӱ��:" + movieHallName);
		System.out.println("����:" + playTime);
		System.out.println("��λ:" + seat);
		System.out.println("�۸�:" + price + "RMB");
		
		
		System.out.println("1.����--��yes����ɣ�no����ʾ���㡿");
		System.out.println("2.��ֵ");
		System.out.println("0.����");
		Integer intChoice = scanner.nextInt();
		switch (intChoice) {
		case 1:
			//����
			order();
			break;
		case 2:
			//��ֵ
			recharge();
			break;
		case 0:
			//����
			choiceSeat();
			break;
		default:
			break;
		}
		
		
		
		
	}
	
	//����
	public static void order() {
		//���
		Double  balance = UserModule.currentBalance;
		if(balance < price) {
			//��ֵ
			System.out.println("����");
			System.out.println("1.��ֵ");
			System.out.println("0.�˳�");
			Integer choice = scanner.nextInt();
			if (1 == choice) {
				//��ֵ
				recharge();
				
			}else {
				System.out.println("�˳��ɹ�");
				System.exit(0);;
			}
		}else {
			//�µ�
			MovieTicket movieTicket = new MovieTicket(seat, playId, UserModule.userId, price, 1);
			Boolean blnResult = movieTicketDao.insertMovieTicket(movieTicket);
			if(blnResult) {
				System.out.println("����ɹ�");
				UserModule.currentBalance -= price;
				userDao.updateBalance(UserModule.currentBalance, UserModule.userId);
			}else
				System.out.println("����ʧ��");
			System.out.println("--------------------------------------------");
			String adminMenuStr =  "1.��������\n"
								 + "2.����\n";
			System.out.println(adminMenuStr);
			System.out.print("��ѡ��:");
			Integer intChoice = scanner.nextInt();
			switch (intChoice) {
			case 1:
				choiceSeat();
				break;
			case 2:
				UserModule.showOnlineMainMenu();
				break;
			default:
				break;
				}
			
			
		}
		

	}
	
	//��ֵ
	public static void recharge() {
		System.out.println("-------------------��ֵ-----------------------");
		
		System.out.print("�������ֵ�Ľ��(RMB):");
		Double balance = scanner.nextDouble();
		UserModule.currentBalance += balance;
		
		Boolean blnResult = userDao.updateBalance(UserModule.currentBalance, UserModule.userId);
		if (blnResult) {
			System.out.println("---��ֵ�ɹ�-----");
			System.out.println("��ǰ���:" + UserModule.currentBalance + "Ԫ");
		}
		System.out.println("0.������ҳ");
		Integer  choice = scanner.nextInt();
		if(0 == choice) {
			UserModule.showOnlineMainMenu();
		}
		
	}
	
	
	//�ҵĵ�ӰƱ
	public static void userMovieTickets() {
		System.out.println("-------------------�ҵĵ�ӰƱ-----------------------");
		List<UserTicket> userTickets = movieTicketDao.selectTicketByUserId(UserModule.userId);
		Integer i = 1;
		for (UserTicket userTicket : userTickets) {
			System.out.println(i + ".��Ӱ��" + userTicket.getMovieName());
			System.out.println("  ӰԺ��" + userTicket.getCinemaName());
			System.out.println("  Ӱ����" + userTicket.getHallName());
			System.out.println("  ��λ��" + userTicket.getSeat());
			System.out.println("  �۸�"+ userTicket.getPrice());
			i++;
		}
		System.out.println("��ѡ���ӰƱ������ۣ�");
		System.out.println("0.����");
		Integer intChoice = scanner.nextInt();
		if(0 != intChoice) {
			//�������
//			System.out.println("���������ۣ�");

			CommentModule.addComment(userTickets.get(intChoice-1).getMovieId(), UserModule.userId);
			
		}else {
			UserModule.showOnlineMainMenu();
		}
		
		
	}
	
	
	
	public static void main(String[] args) {
		listMovie();
//		choiceSeat();
	}
	
	
}
