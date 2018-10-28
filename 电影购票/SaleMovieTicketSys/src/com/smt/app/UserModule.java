package com.smt.app;

import java.util.Scanner;

import com.smt.dao.MovieDao;
import com.smt.dao.UserDao;
import com.smt.entity.Movie;
import com.smt.entity.MovieTicket;
import com.smt.entity.User;

public class UserModule {
	private static UserDao userDao = new UserDao();
	private static MovieDao movieDao = new MovieDao();
	private static Scanner scanner = new Scanner(System.in);
	public static Integer userId;
	public static User currentUser = new User();
	public static Double currentBalance;
	
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
				userLogin();
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
			userId = user.getId();
			currentUser = user;
			currentBalance = user.getBalance();
			showOnlineMainMenu();
		}
	}
	
	
	//��¼�����ҳ
	public static void showOnlineMainMenu() {
		System.out.println("-------------------��¼�ɹ�����ҳ��-----------------------");
		System.out.println("1.��Ʊ");
		System.out.println("2.��Ӱ��ѯ");
		System.out.println("3.��ֵ");
		System.out.println("4.�ҵĵ�ӰƱ�����ۡ�");
		System.out.println("5.������Ϣ");
		System.out.println("6.�˳�");
		System.out.print("��ѡ��:");
		Integer intChoice = scanner.nextInt();
		switch (intChoice) {
		case 1:
			//��Ʊ
			MovieTicketModule.listMovie();
			break;
		case 2:
			//��Ӱ��ѯ
			searchMovie();
			break;
		case 3:
			//��ֵ
			MovieTicketModule.recharge();
			break;
		case 4:
			//�ҵĵ�ӰƱ�����ۡ�
			MovieTicketModule.userMovieTickets();
			break;
		case 5:
			//������Ϣ
			
			break;
		case 6:
			//�˳�
			System.exit(0);
			break;
		default:
			break;
		}
		
		
	}
	
	
	
	
	
	//��ѯ��Ӱ
	public static void searchMovie() {
		System.out.println("-------------------��Ӱ��ѯ-----------------------");
		System.out.print("�����Ӱ����");
		System.out.println("\n\n0.����");
		String movieName = scanner.next();
		if("0" != movieName) {
			Movie movie = movieDao.searchMovieByName(movieName);
//			System.out.println(movie);
			if(null == movie.getId()) {
				System.out.println("û���ҵ��˵�Ӱ��\r\n" + 
						"��ѯʧ�ܣ����½��в�����");
				searchMovie();
			}else {
				System.out.println("----------------------��Ӱ����----------------------");
				System.out.println(movie.getName());
				System.out.println("��Ա:" + movie.getActors());
				System.out.println("ʱ��:" + movie.getDuration() + "����");
				System.out.println("����:" + movie.getType());
				System.out.println("��ӳʱ��:" + movie.getReleaseTime());
				System.out.println("����:" + ((movie.getScore()==0)?"��������":movie.getScore()));
				System.out.println("\n1.����");
				searchMovie();
			}
		}else {
			showOnlineMainMenu();
		}
		
	}
	
}
