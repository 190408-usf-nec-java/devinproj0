package com.revature.views;

import com.revature.accounts.AccountActions;
import com.revature.banking.GetInput;

public class AccountView implements View {

	@Override
	public View printOptions() {
		System.out.println("\nHere are your accounts please select what you want to do: ");
		if(!HelperViews.showAccounts()) {
			return new AccountView();
		}
		System.out.println("1. Create a new bank account");
		System.out.println("2. Add someone to an exsiting bank account");
		System.out.println("3. Delete a bank account");
		System.out.println("4. Go back to main menu\n");
		AccountActions acc = new AccountActions();
		
		int input = GetInput.getCommand();
		View view = null;
		switch(input) {
		case 1:
			view = acc.createBankAcc();
			break;
		case 2:
			view = acc.addUserToAcct();
			break;
		case 3:
			view = acc.deleteBankAcc();
			break;
		case 4:
			view = new MainMenu();
			break;
		default:
			view = new AccountView();
			break;
		}
		return view;
	}

}
