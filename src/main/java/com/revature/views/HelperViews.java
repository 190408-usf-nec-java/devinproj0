package com.revature.views;

import java.util.List;

import com.revature.banking.Actions;
import com.revature.banking.Session;
import com.revature.exceptions.DataBaseException;

public class HelperViews {

	public static boolean showAccounts() {
		// TODO Auto-generated method stub
		List<Long> accounts;
		try {
			accounts = Actions.getAccounts(Session.getUsr().getUserName());
		} catch (DataBaseException e) {
			System.out.println("Something went wrong, please try again later.");
			return false;
		}
		if(accounts.isEmpty()) {
			System.out.println("\n****************************************************************");
			System.out.println("You have no accounts, please go create one in account management");
			System.out.println("****************************************************************\n");
			return true;
		}
		accounts.forEach(account -> {
			System.out.println(account);
		});
		return true;
	}

	public static void whatNext() {
		// TODO Auto-generated method stub
		System.out.println("\nWhat else would you like to do?");
		System.out.println("*******************************************\n");
	}

}
