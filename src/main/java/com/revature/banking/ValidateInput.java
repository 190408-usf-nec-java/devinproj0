package com.revature.banking;

public class ValidateInput {
	public static boolean validateUsr(String input) {
		char[] chars = input.toCharArray();
		if(chars.length <= 3 && !input.contentEquals("1")) {
			return false;
		}else if(input.contentEquals("1")) {
			return true;
		}
		for(int i=0; i< chars.length; i++) {
			if(!Character.isLetterOrDigit(chars[i])) {
				return false;
			}
		}
		return true;
		
	}
	public static boolean validatePwd(String input) {
		if(input.length() < 6) {
			return false;
		}else {
			return true;
		}
	}
	public static boolean validateBankAcc(String input) {
		long account;
		try {
			account = Long.parseLong(input);
		}catch(Exception e) {
			return false;
		}
		if(account < 0) {
			return false;
		}
		return true;
	}
	public static boolean validateMoney(String input) {
		double money;
		try {
			money = Double.parseDouble(input);
		}catch(Exception e) {
			return false;
		}
		if(money < 0) {
			return false;
		}
		return true;
	}
	public static boolean validateCmd(String input){
		@SuppressWarnings("unused")
		int result;
		input = input.toLowerCase();
		try{
			result = Integer.parseInt(input);
		}catch(Exception e){
			return false;
		}
		return true;
	}
	public static boolean validateacctType(String input) {
		input = input.toLowerCase();
		if(!(input.contentEquals("joint") || input.contentEquals("personal"))) {
			return false;
		}else {
			return true;
		}
	}
}
