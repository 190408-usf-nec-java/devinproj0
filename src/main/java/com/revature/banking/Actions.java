package com.revature.banking;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.revature.bankdao.Dao;
import com.revature.beans.User;
import com.revature.exceptions.DataBaseException;

public class Actions {
	private static Dao dao = new Dao();
	
	public static boolean withdrawMoney(double amount, long account) throws DataBaseException{
		double check=0;
		check = dao.getBalance(account);
		if(amount > check) {
			return false;
		}
		
		dao.withdrawFromAccount(account, amount);
		return true;
		
	}

	public static boolean login(User user) throws DataBaseException{
		User checkUser=null;
		checkUser = dao.getLogin(user.getUserName());
		if(checkUser.getUserName().contentEquals("")) {
			return false;
		}else if(checkUser.equals(user)) {
				return true;
		}else {
			return false;
		}
	}

	public static boolean checkIfUserExists(String username) throws DataBaseException{
		if(dao.userExists(username)) {
			return true;
		}else {
			return false;
		}
	}

	public static void createUser(String username, String password) throws DataBaseException {
		User user = new User(username, password);
		dao.createNewUser(user);
		
	}

	public static boolean pullAccount(long account) throws DataBaseException {
	
		if(dao.accountExists(account)) {
			return true;
		}else {
			return false;
		}
	}

	public static List<Long> getAccounts(String username) throws DataBaseException {
		return dao.getAccountsByUser(username);
	}

	public static double getAccountBalance(Long account) throws DataBaseException {
		return dao.getBalance(account);
	}

	public static boolean transferMoney(double amount, long account1, long account2) throws DataBaseException {
		double balance = dao.getBalance(account1);
		if(balance < amount) {
			return false;
		}
		dao.transferFunds(account1, account2, amount);
		return true;
	}

	public static boolean pullUserAccount(long account, String username) throws DataBaseException {
		Set<String> storedUsers = new HashSet<String>();
		storedUsers = dao.getUserByAccount(account);
		if(storedUsers.contains(username)) {
			return true;
		}else {
			return false;
		}
	}

	public static long createBankAccount(String username) throws DataBaseException {
		return dao.addBankAccountByUser(username);
	}

	public static void addUserToAcct(User user, long account) throws DataBaseException {
		dao.addUserToAccount(user.getUserName(), account);
	}

	public static void deleteAccount(long account) throws DataBaseException {
		dao.deleteAccountByAccountNumber(account);
	}

	public static boolean depositMoney(double amount, long account) throws DataBaseException{
		if(amount <= 0) {
			return false;
		}
		dao.depositToAccount(account, amount);
		return true;
	}

}
