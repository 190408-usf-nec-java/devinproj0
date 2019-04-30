package com.revature.banking;

import com.revature.beans.User;

public class Session {
	private static boolean logoff = false;
	private static User usr;
	private static boolean firstTime;
	
	public Session() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static boolean isLogoff() {
		return Session.logoff;
	}

	public static void setLogoff(boolean logoff) {
		Session.logoff = logoff;
	}

	public static User getUsr() {
		return usr;
	}

	public static void setUsr(User usr) {
		Session.usr = usr;
	}

	public static boolean isFirstTime() {
		return firstTime;
	}

	public static void setFirstTime(boolean firstTime) {
		Session.firstTime = firstTime;
	}
	
}
