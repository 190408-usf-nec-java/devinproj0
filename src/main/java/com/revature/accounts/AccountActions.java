package com.revature.accounts;

import com.revature.banking.Actions;
import com.revature.banking.GetInput;
import com.revature.banking.Session;
import com.revature.beans.User;
import com.revature.exceptions.DataBaseException;
import com.revature.views.AccountView;
import com.revature.views.HelperViews;
import com.revature.views.TransferView;
import com.revature.views.View;

public class AccountActions {
	/**
	 * 
	 * @return
	 */
	public View deleteBankAcc() {
		long account=0L;
		System.out.println("Here are your accounts");
		HelperViews.showAccounts();
		System.out.println("Please enter the one you want to delete:");
		account = GetInput.getAccount();
		try {
			if(!Actions.pullUserAccount(account, Session.getUsr().getUserName())) {
				System.out.println("That account either does not exist or belong to you");
				return new AccountView();
			}if(Actions.getAccountBalance(account)>0L) {
				System.out.println("You still have money in that account. Please transfer or withdraw your funds before deleting.");
				return new TransferView();
			}
		}catch(DataBaseException e) {
			System.out.println("Something went wrong, please try again later.");
			return new AccountView();
		}
		boolean validInput = false;
		int cmd=0;
		while(!validInput) {
			System.out.println("You are about to delete account "+account);
			System.out.println("This action is irreversable, are you sure you want to do this?");
			System.out.println("1. Yes, i'm sure");
			System.out.println("2. No, go back to accounts menu");
			cmd = GetInput.getCommand();
			switch(cmd) {
			case 1:
				validInput = true;
				break;
			case 2:
				return new AccountView();
			default:
				System.out.println("Invalid command, please try again");
				continue;
				
			}
		}
		try{
			Actions.deleteAccount(account);
		}catch(DataBaseException e) {
			System.out.println("Something went wrong, aborting deletion. Please try again later.");
			return new AccountView();
		}
		System.out.println("Deleted account "+ account+ " successfully");
		HelperViews.whatNext();
		return new AccountView();
	}
	/**
	 * 
	 * @return
	 * 
	 */
	public View addUserToAcct(){
		long account=0L;
		String username="";
		String password="";
		System.out.println("Please have the person you want to join the account enter their username:");
		username = GetInput.getUsername();
		try {
			if(!Actions.checkIfUserExists(username)) {
				System.out.println("That is not a user that exists");
				return new AccountView();
			}
		} catch (DataBaseException e1) {
			System.out.println("Something went wrong, please try again later.");
			return new AccountView();
		}
		boolean validInput = false;
		while(!validInput) {
			System.out.println("Please put in your password:");
			password = GetInput.getPassword();
			validInput = true;
		}
		User user = new User(username, password);
		try {
			if(!Actions.login(user)) {
				System.out.println("Sorry, that login is incorrect.");
				return new AccountView();
			}
		}catch(DataBaseException e) {
			System.out.println("Sorry, something went wrong, please try again later.");
		}
		validInput = false;
		while(!validInput) {
			System.out.println("Here are your accounts, please enter one to add the user to");
			HelperViews.showAccounts();
			account = GetInput.getAccount();
			try{
				if(!Actions.pullUserAccount(account, Session.getUsr().getUserName())) {
					System.out.println("Sorry, that account either doesn't exist or belong to you");
					continue;
				}
			}catch(DataBaseException e) {
				System.out.println("Something went wrong, aborted");
				return new AccountView();
			}
			validInput = true;
		}
		System.out.println("\n*****************************************************************************************");
		System.out.println("You are about to add "+ username + " to account " + account);
		System.out.println("This action is irreversable and will allow " + username + " full access to your account.");
		System.out.println("Are you sure you want to proceed?");
		System.out.println("*****************************************************************************************");
		System.out.println("\n1. Yes, i'm sure.");
		System.out.println("2. No, go back to account management");
		validInput=false;
		while(!validInput) {
			int cmd = GetInput.getCommand();
			switch(cmd) {
			case 1:
				validInput = true;
				break;
			case 2:
				return new AccountView();
			default:
				System.out.println("That is not a valid command");
				continue;
			}
		}
		System.out.println("Adding "+ username + " to account");
		try{ 
			Actions.addUserToAcct(user, account);
		}catch(DataBaseException e) {
			System.out.println("Something went wrong, aborted");
			return new AccountView();
		}
		System.out.println("Added "+user.getUserName()+" to the account.");
		HelperViews.whatNext();
		return new AccountView();
	}
	/**
	 * 
	 * @return
	 */
	public View createBankAcc() {
		try{
			long account = Actions.createBankAccount(Session.getUsr().getUserName());
			System.out.println("Account created successfully.");
			System.out.println("New bank account number = " + account);
		}catch(DataBaseException e) {
			//log this shit
			System.out.println("Account could not be created. Something has gone wrong");
		}
		return new AccountView();
	}
}
