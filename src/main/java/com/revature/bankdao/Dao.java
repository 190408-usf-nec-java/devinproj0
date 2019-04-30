package com.revature.bankdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.banking.Session;
import com.revature.beans.User;
import com.revature.exceptions.DataBaseException;


public class Dao {
	final static Logger logger = Logger.getRootLogger();
	
	public List<Long> getAccountsByUser(String username) throws DataBaseException{
		List<Long> accounts = new ArrayList<Long>();
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT account FROM users_accounts where username = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				accounts.add(rs.getLong("account"));
			}
		} catch (SQLException e) {
			logger.error(e);
			throw new DataBaseException();
		}
		return accounts;
	}

	public double getBalance(Long account) throws DataBaseException{
		double balance=0;
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT balance FROM accounts where account_num = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, account);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				balance = rs.getDouble("balance");
			}
		} catch (SQLException e) {
			logger.error(e);
			throw new DataBaseException();
		}
		return balance;
	}

	public Set<String> getUserByAccount(long account) throws DataBaseException{
		Set<String> usernames = new HashSet<String>(); 
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT username FROM users_accounts where account = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, account);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				usernames.add(rs.getString("username"));
			}
		} catch (SQLException e) {
			logger.error(e);
			throw new DataBaseException();
		}
		return usernames;
	}

	public long addBankAccountByUser(String username) throws DataBaseException{
		long accountNum=0;
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT account_num from accounts order by account_num desc limit 1;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				accountNum = rs.getLong("account_num");
			}
		} catch (SQLException e) {
			logger.error(e);
			accountNum = -1L;
		}
		accountNum+=1;
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO accounts (account_num, balance) VALUES (?,'0.00'); "
					+ "INSERT INTO users_accounts (account, username) VALUES (?,?); ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, accountNum);
			ps.setLong(2, accountNum);
			ps.setString(3, Session.getUsr().getUserName());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			logger.error(e);
			throw new DataBaseException();
		}
		return accountNum;
	}

	public void addUserToAccount(String userName, long account) throws DataBaseException{
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO users_accounts (username, account) VALUES (?,?); ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setLong(2, account);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			logger.error(e);
			throw new DataBaseException();
		}
	}

	public void deleteAccountByAccountNumber(long account) throws DataBaseException{
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "DELETE FROM users_accounts where account = ?; ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, account);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			logger.error(e);
			throw new DataBaseException();
		}
	}

	public void withdrawFromAccount(long account, double amount) throws DataBaseException {
		double balance = getBalance(account);
		amount = balance - amount;
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "UPDATE accounts SET balance = ? where account_num = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, amount);
			ps.setLong(2, account);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			logger.error(e);
			throw new DataBaseException();
		}
	}

	public User getLogin(String userName) throws DataBaseException{
		User user = new User();
		String username="";
		String password="";
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT user_name, password FROM users where user_name = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				username = rs.getString("user_name");
				password = rs.getString("password");
			}
			
		} catch (SQLException e) {
			logger.error(e);
			throw new DataBaseException();
		}
		user.setUserName(username);
		user.setPassword(password);
		return user;
	}
	/**
	 * 
	 * @param username
	 * @return
	 * @throws DataBaseException
	 */
	public boolean userExists(String username) throws DataBaseException{
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT user_name FROM users where user_name=?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}else {
				return false;
			}
			
		} catch (SQLException e) {
			logger.error(e);
			throw new DataBaseException();
		}
	}

	public void createNewUser(User user) throws DataBaseException{
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO users (user_name, password) VALUES (?,?);";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassword());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			logger.error(e);
			throw new DataBaseException();
		}
		
	}

	public boolean accountExists(long account) throws DataBaseException{
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT account_num FROM accounts where account_num = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, account);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}else {
				return false;
			}
			
		} catch (SQLException e) {
			logger.error(e);
			throw new DataBaseException();
		}
	}

	public void depositToAccount(long account, double amount) throws DataBaseException {
		amount = getBalance(account) + amount;
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "UPDATE accounts SET balance=? WHERE account_num = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, amount);
			ps.setLong(2, account);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			logger.error(e);
			throw new DataBaseException();
		}
		
	}

	public void transferFunds(long account1, long account2, double amount) throws DataBaseException{
		try(Connection conn = ConnectionUtil.getConnection()) {
			double balance = getBalance(account1);
			String sql = "UPDATE accounts SET balance=? WHERE account_num = ?;"+ 
					"UPDATE accounts SET balance = ? where account_num = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, balance-amount);
			ps.setLong(2, account1);
			balance = getBalance(account2);
			ps.setDouble(3, balance+amount);
			ps.setLong(4, account2);
			conn.setAutoCommit(false);
			ps.executeUpdate();
			conn.commit();
			
		} catch (SQLException e) {
			logger.error(e);
			throw new DataBaseException();
		}
	}
}
