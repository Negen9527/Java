package com.smt.utils;

import java.util.Scanner;

public class InputUtils {
	
	private static Scanner scanner = new Scanner(System.in);
	public static Integer checkInputValue(Integer start,Integer end) {
		int choice = scanner.nextInt();
		while(true) {
			if (choice < start || choice > end) {
				System.out.printf("������%d-%d֮�������\n",start,end);
				choice = scanner.nextInt();
			}else {
				return choice;
			}
		}
	}

}
