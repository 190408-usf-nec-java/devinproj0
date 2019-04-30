package com.revature.banking;

import com.revature.views.*;

public class BankingLauncher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean logoff = false;
		View view = new LoginView();
		while(!logoff){
			view = view.printOptions();
			logoff = Session.isLogoff();
		}
	}

}
