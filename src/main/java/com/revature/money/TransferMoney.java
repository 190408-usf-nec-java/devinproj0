package com.revature.money;

import com.revature.banking.Actions;
import com.revature.banking.GetInput;
import com.revature.banking.Session;
import com.revature.exceptions.DataBaseException;
import com.revature.views.HelperViews;
import com.revature.views.MainMenu;
import com.revature.views.TransferView;
import com.revature.views.View;

public class TransferMoney {
	public View tranferFunds(){
		System.out.println("Please enter the account you would like to transfer funds from:");
		long account1=GetInput.getAccount();
		try {
			if(!Actions.pullUserAccount(account1, Session.getUsr().getUserName())) {
				System.out.println("That is not one of your accounts.");
				return new TransferView();
			}
			System.out.println("Please enter the account you are transferring into:");
			long account2=GetInput.getAccount();
			if(!Actions.pullAccount(account2)) {
				System.out.println("That account does not exist");
				return new TransferView();
			}
			System.out.println("Please enter the amount you would like to transfer:");
			double amount = GetInput.getMoneyAmount();
			if(Actions.transferMoney(amount, account1, account2)) {
				System.out.println("You have successfully transferred: $"+amount+" to "+ account2);
				HelperViews.whatNext();
				return new MainMenu();
			}else {
				System.out.println("You don't have that much money.");
				return new TransferView();
			}
		}catch(DataBaseException e) {
			System.out.println("Something went wrong, please try again later.");
			return new TransferView();
		}
	}
}
