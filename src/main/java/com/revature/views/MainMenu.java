package com.revature.views;

import com.revature.banking.GetInput;

public class MainMenu implements View {
	@Override
	public View printOptions() {
		System.out.println("\n***********************************************");
		System.out.println("1. manage bank accounts");
		System.out.println("2. deposit/withdraw or check balance");
		System.out.println("3. transfer funds from an account");
		System.out.println("4. logs you off");
		System.out.println("***********************************************");
		System.out.println("Please input your command:");
		
		int input = GetInput.getCommand();
		switch(input) {
		case 1:
			return new AccountView();
		case 2:
			return new MoneyView();
		case 3:
			return new TransferView();
		case 4:
			return new LogOffView();
		default :
			return new MainMenu();
				
			
		}
	}

}