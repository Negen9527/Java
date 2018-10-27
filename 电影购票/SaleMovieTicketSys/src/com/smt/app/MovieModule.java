package com.smt.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.smt.dao.MovieDao;
import com.smt.entity.Movie;
import com.smt.utils.DateUtil;

//��Ӱ����ģ��(����)
public class MovieModule {
	
	private static MovieDao movieDao = new MovieDao();
	private static Scanner scanner = new Scanner(System.in);
	private static Integer currentPage = 1;
	/**
	 * 1.��Ӱ����ѡ��˵�
	 */
	public static void adminMovieMenu() {
		System.out.println("----------------------����̨��ҳ��----------------------");
		String adminMenuStr =  "1.��Ӱ�б�\n"
							+ "2.��ӵ�Ӱ\n"
							+ "3.����\n"

							+ "6.�˳�";
		
		System.out.println(adminMenuStr);
		System.out.print("��ѡ��:");
		int choice = scanner.nextInt();
		switch (choice) {
		case 1:
			//��Ӱ�б�
			listMovie();
			break;
		case 2:
			//��ӵ�Ӱ
			addMovie();
			break;
		case 3:
			//����
			MainApp.adminMenu();
			break;
		case 6:
			//�˳�
			System.exit(0);
		default:
			break;
		}
		
	}
	
	
	
	
	
	//��ӵ�Ӱ
	public static void addMovie() {
		System.out.println("�������Ӱ��:");
		String name = scanner.next();
		System.out.println("��������Ա:");
		String actors = scanner.next();
		System.out.println("����������:");
		String type = scanner.next();
		System.out.println("������ʱ��:");
		String duration = scanner.next();
		System.out.println("��������ӳʱ��(�磺2017-10-10):");
		String releaseTimeStr = scanner.next();
		Date releaseTime = DateUtil.str2date(releaseTimeStr);
		Movie movie = new Movie();
		movie.setName(name);
		movie.setActors(actors);
		movie.setType(type);
		movie.setDuration(Double.parseDouble(duration));
		movie.setReleaseTime(releaseTime);
		Boolean blnResult = movieDao.insertMovie(movie);
//		System.out.println(blnResult);
		if(blnResult)
			System.out.println("��ӳɹ�");
		else
			System.out.println("���ʧ��");
		System.out.println("--------------------------------------------");
		String adminMenuStr =  "1.�������\n"
							 + "2.����\n";
		System.out.println(adminMenuStr);
		System.out.print("��ѡ��:");
		Integer intChoice = scanner.nextInt();
		switch (intChoice) {
		case 1:
			addMovie();
			break;
		case 2:
			MainApp.adminMenu();
			break;
		default:
			break;
		}
	
	}
	
	//�鿴��Ӱ
	public static void listMovie() {
		List<Movie> movies = movieDao.selectAllMovie();
		Map<Integer, List<Movie>> pageTeam = new HashMap<>();     //��ҳ
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
		
		showMovieList(pageTeam);
		
		
		
		
		
	}
	
	//��ʾ��Ӱ�б�
	private static void showMovieList(Map<Integer, List<Movie>> pageTeam) {
		Integer totalPage = pageTeam.size();
		System.out.println("----------------------��Ӱ�б�----------------------");
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
		switch (choice) {
		case 7:
			//��һҳ
			currentPage --;
			if(currentPage < 1) {
				System.out.println("û����һҳ");
				currentPage ++;
				showMovieList(pageTeam);
			}else {
				showMovieList(pageTeam);
			}
			break;
		case 8:
			//��һҳ
			currentPage ++;
			if(currentPage > totalPage) {
				System.out.println("û����һҳ");
				currentPage--;
				showMovieList(pageTeam);
			}else {
				showMovieList(pageTeam);
			}
			break;
		case 9:
			//����
			adminMovieMenu();
			break;
		default:
			break;
		}
	}
	
	
	
	//����
	public static void main(String[] args) {
		MovieModule.listMovie();
	}
	
	
	
}
