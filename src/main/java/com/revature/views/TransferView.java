package com.revature.views;

import com.revature.banking.GetInput;
import com.revature.money.TransferMoney;

public class TransferView implements View {

	@Override
	public View printOptions() {
		System.out.println("\nHere are your accounts:");
		if(!HelperViews.showAccounts()) {
			return new AccountView();
		}
		System.out.println("Please select what you would like to do:");
		System.out.println("1. Transfer funds to another account");
		System.out.println("2. Back to main menu"); 
		TransferMoney transfer = new TransferMoney();
		View view = null;
		int input = GetInput.getCommand();
		switch(input){
		case 1:
			view = transfer.tranferFunds();
			break;
		case 2:
			view = new MainMenu();
			break;
		default:
			System.out.println("Not a valid input, please try again.");
			view = new TransferView();
		}
		return view;
	}

}
