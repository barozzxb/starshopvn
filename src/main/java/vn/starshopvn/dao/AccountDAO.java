package vn.starshopvn.dao;

import java.util.List;

import vn.starshopvn.entity.Account;

public interface AccountDAO {

	void insert(Account account);

	void deleteById(String id);

	void update(Account account);

	int count();

	Account findById(String id);

	List<Account> findAll();

	List<Account> top5Account();

	void setActive(String userid);

	void setDeactive(String userid);

	Account findByEmail(String email);
}
