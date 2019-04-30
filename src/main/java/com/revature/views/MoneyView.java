package com.revature.views;

import com.revature.banking.GetInput;
import com.revature.money.MoneyActions;

public class MoneyView implements View {

	@Override
	public View printOptions() {
		System.out.println("\nPlease select what you would like to do:");
		System.out.println("1. Deposit money");
		System.out.println("2. Withdraw money");
		System.out.println("3. Check balances on your accounts");
		System.out.println("4. Back to main menu");
		
		MoneyActions monmon = new MoneyActions();
		
		View view = null;
		int input = GetInput.getCommand();
		switch(input) {
		case 1:
			view = monmon.depositFunds();
			break;
		case 2:
			view = monmon.withdrawFunds();
			break;
		case 3:
			view = monmon.getBalance();
			break;
		case 4:
			view = new MainMenu();
			break;
		default :
			System.out.println("Not a valid command, please try again");
			view = new MoneyView();
		}
		return view;
	}

}
