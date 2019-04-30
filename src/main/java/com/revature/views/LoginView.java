package com.revature.views;

import com.revature.banking.GetInput;
import com.revature.useractions.UserActions;

public class LoginView implements View {

	@Override
	public View printOptions() {
		
		System.out.println("Welcome to the secure swiss bank remote access.");
		System.out.println("Please choose what you want to do:");
		System.out.println("1. Log in");
		System.out.println("2. Create new account");
		View view = null;
		int input = GetInput.getCommand();
		UserActions usr = new UserActions();
		switch(input){
		case 2:
			view = usr.createUser();
			break;
		case 1:
			view = usr.login();
			break;
		default:
			System.out.println("Not a valid command.");
			view = new LoginView();
			break;
		}
		
		return view;
	}

}
