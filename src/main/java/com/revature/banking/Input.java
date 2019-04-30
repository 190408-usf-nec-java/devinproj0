package com.revature.banking;

import java.util.Scanner;

public class Input {
	private static Scanner scanner = new Scanner(System.in);

	public static String getInput() {
		
		String input;
		input = scanner.nextLine();
		return input;
	}
	
	public static void cleanup() {
		scanner.close();
	}
}
