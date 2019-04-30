package com.revature.useractions;

import com.revature.banking.Actions;
import com.revature.banking.GetInput;
import com.revature.banking.Session;
import com.revature.beans.User;
import com.revature.exceptions.DataBaseException;
import com.revature.views.LoginView;
import com.revature.views.MainMenu;
import com.revature.views.View;

public class UserActions {
	public View login(){
		String username="";
		String password="";
		System.out.println("Please enter your username:");
		username = GetInput.getUsername();
		System.out.println("Please enter your password:");
		password = GetInput.getPassword();
		User user = new User(username, password);
		try {
			if(Actions.login(user)) {
				Session.setUsr(user);
				return new MainMenu();
			}else {
				System.out.println("Your login was incorrect, please try again");
				return new LoginView();
			}
		} catch (DataBaseException e) {
			System.out.println("Something went wrong, please try again later.");
			return new LoginView();
		}
	}
	
	public View createUser() {
		System.out.println("Please enter the new username you want to use:");
		String username="";
		String password="";
		username = GetInput.getUsername();
		try {
			if(Actions.checkIfUserExists(username)) {
				System.out.println("That username is already taken, please try another one.");
				return new LoginView();
			}
		} catch (DataBaseException e) {
			System.out.println("Something went wrong, please try again later.");
			return new LoginView();
		}
		System.out.println("Please enter a new password ** WARNING ** this cannot be recovered:");
		password = GetInput.getPassword();
		User user = new User(username, password);
		try {
			Actions.createUser(username, password);
		} catch (DataBaseException e) {
			System.out.println("Something went wrong, please try again later.");
			return new LoginView();
		}
		Session.setUsr(user);
		return new MainMenu();
	}
}
