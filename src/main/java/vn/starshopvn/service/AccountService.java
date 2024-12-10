package vn.starshopvn.service;

import java.util.List;

import vn.starshopvn.entity.Account;

public interface AccountService {

	boolean insert(Account account);

	boolean deleteById(String id);
	
	boolean update(Account account);

	int count();

	Account findById(String id);

	List<Account> findAll();

	Account login(String userid, String password);

	List<Account> top5Account();

	boolean setActive(String userid);

	boolean setDeactive(String userid);
	
	Account findByEmail(String email);
}
