package com.revature.views;

import com.revature.banking.Input;
import com.revature.banking.Session;

public class LogOffView implements View {

	@Override
	public View printOptions() {
		Input.cleanup(); //closing the scanner
		System.out.println("Logged off");
		Session.setLogoff(true);
		return new LogOffView();
	}

}
