package com.revature.money;

import java.util.List;

import com.revature.banking.Actions;
import com.revature.banking.GetInput;
import com.revature.banking.Session;
import com.revature.exceptions.DataBaseException;
import com.revature.views.AccountView;
import com.revature.views.HelperViews;
import com.revature.views.MainMenu;
import com.revature.views.MoneyView;
import com.revature.views.View;

public class MoneyActions {
	public View withdrawFunds() {
		System.out.println("Please input the account you would withdraw from:");
		if(!HelperViews.showAccounts()) {
			return new AccountView();
		}
		long account = GetInput.getAccount();
		try {
			if(!Actions.pullUserAccount(account, Session.getUsr().getUserName())) {
				System.out.println("That is not one of your accounts.");
				return new MoneyView();
			}else {
				double amount=0;
				System.out.println("Please enter the amount of money you would like to withdraw.");
				amount = GetInput.getMoneyAmount();
				if(Actions.withdrawMoney(amount, account)) {
					System.out.println("You have successfully withdrawn: $"+amount);
					HelperViews.whatNext();
					return new MoneyView();
				}else {
					System.out.println("You don't have that much money.");
					return new MoneyView();
				}
			}
		} catch (DataBaseException e) {
			System.out.println("Something went wrong, please try again later.");
			return new MoneyView();
		}
	}

	public View depositFunds() {
		long account = 0;
		System.out.println("Here are your accounts:");
		if(!HelperViews.showAccounts()) {
			return new AccountView();
		}
		System.out.println("Please choose which account you would like to deposit into:");
		account = GetInput.getAccount();
		try {
			if(!Actions.pullUserAccount(account, Session.getUsr().getUserName())) {
				System.out.println("That is not one of your accounts.");
				return new MoneyView();
			}else {
				double amount=0;
				System.out.println("Please enter the amount you would like to deposit:");
				amount = GetInput.getMoneyAmount();
				if(Actions.depositMoney(amount, account)) {
					System.out.println("You have successfully deposited: $"+(double)Math.round(amount * 100d) / 100d);
					HelperViews.whatNext();
					return new MainMenu();
				}else {
					System.out.println("Something has gone wrong.");
					return new MoneyView();
				}
			}
		} catch (DataBaseException e) {
			System.out.println("Something went wrong, please try again later.");
			return new MoneyView();
		}
	}
	public View getBalance() {
		System.out.println("Here are your account balances:");
		List<Long> accounts;
		try {
			accounts = Actions.getAccounts(Session.getUsr().getUserName());
			accounts.forEach(account -> {
				try {
					System.out.println(account +": $"+ (double)Math.round((Actions.getAccountBalance(account)) * 100d) / 100d);
				} catch (DataBaseException e) {
					System.out.println("Something went wrong, please try again later.");
				}
			});
		} catch (DataBaseException e) {
			System.out.println("Something went wrong, please try again later.");
			return new MoneyView();
		}
		if(accounts.isEmpty()) {
			System.out.println("****************************************************************");
			System.out.println("You have no accounts, please go create one in account management");
			System.out.println("****************************************************************");
			return new AccountView();
		}
		HelperViews.whatNext();
		return new MoneyView();
	}
}
