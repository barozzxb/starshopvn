package vn.starshopvn.service.impl;

import java.util.List;

import vn.starshopvn.dao.AccountDAO;
import vn.starshopvn.dao.impl.AccountDAOImpl;
import vn.starshopvn.entity.Account;
import vn.starshopvn.service.AccountService;

public class AccountServiceImpl implements AccountService{

	AccountDAO aDAO = new AccountDAOImpl();
	
	@Override
	public boolean insert(Account account) {
		try {
			aDAO.insert(account);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteById(String id) {
		try {
			aDAO.deleteById(id);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Account account) {
		try {
			aDAO.update(account);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public int count() {
		return aDAO.count();
	}

	@Override
	public Account findById(String id) {
		return aDAO.findById(id);
	}

	@Override
	public List<Account> findAll() {
		return aDAO.findAll();
	}

	@Override
	public Account login(String userid, String password) {
		Account acc = aDAO.findById(userid);
		if (acc != null) {
			if (password.equals(acc.getPassword())) {
				return acc;
			} else return null;
		} else return null;
	}

	@Override
	public List<Account> top5Account() {
		return aDAO.top5Account();
	}
	
	@Override
	public boolean setDeactive(String userid) {
		try{
			aDAO.setDeactive(userid);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean setActive(String userid) {
		try{
			aDAO.setActive(userid);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
