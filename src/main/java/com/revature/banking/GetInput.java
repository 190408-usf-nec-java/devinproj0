package com.revature.banking;

import com.revature.exceptions.GetOutOfJailFreeException;

public class GetInput {
	/**
	 * gets a username from the user
	 * @return String of user input
	 * @throws IllegalArgumentException
	 * @throws GetOutOfJailFreeException 
	 */
	public static String getUsername(){
		boolean validInput = false;
		String input="";
		while(!validInput) {
			input = Input.getInput();
			validInput = ValidateInput.validateUsr(input);
			if(!validInput) {
				System.out.println("Not valid input, please try again:");
			}
		}
		return input;
	}
	/**
	 * gets a password from the users
	 * @return String of user input
	 * @throws IllegalArgumentException
	 */
	public static String getPassword(){
		boolean validInput = false;
		String input="";
		while(!validInput) {
			input = Input.getInput();
			validInput = ValidateInput.validatePwd(input);
			if(!validInput) {
				System.out.println("Not valid input, please try again:");
			}
		}
		return input;
	}
	/**
	 * gets a command from the user
	 * @return integer command from the user
	 * @throws NumberFormatException
	 */
	public static int getCommand(){
		boolean validInput=false;
		String input="";
		int intPut=0;
		while(!validInput) {
			input = Input.getInput();
			validInput = ValidateInput.validateCmd(input);
			if(!validInput) {
				System.out.println("Not valid input, please try again:");
			}
		}
		intPut = Integer.parseInt(input);
		return intPut;
	}
	/**
	 * Gets a bank account from the user
	 * @return long account input
	 * @throws IllegalArgumentException
	 */
	public static long getAccount(){
		boolean validInput=false;
		String input="";
		long account=0L;
		while(!validInput) {
			input = Input.getInput();
			validInput = ValidateInput.validateBankAcc(input);
			if(!validInput) {
				System.out.println("Not valid input, please try again:");
			}
		}
		account = Long.parseLong(input);
		return account;
	}
	/**
	 * Gets a money amount from the user
	 * @return double an amount of money
	 * @throws IllegalArgumentException
	 */
	public static double getMoneyAmount(){
		boolean validInput=false;
		String input="";
		double amount=0D;
		while(!validInput) {
			input = Input.getInput();
			validInput = ValidateInput.validateMoney(input);
			if(!validInput) {
				System.out.println("Not valid input, please try again:");
			}
		}
		
		amount = Double.parseDouble(input);
		return amount;
	}

}
