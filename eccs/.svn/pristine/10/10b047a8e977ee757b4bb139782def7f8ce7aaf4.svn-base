package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.smart.model.Account;
import com.smart.dao.AccountDao;

/**
 * AccountService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class AccountService {

	@Autowired
	private AccountDao accountDao;

	// ====================== 基本 C R U D 方法 ===========================
	public Account get(int id) {
		return accountDao.get(id);
	}

	public void save(Account entity) {
		accountDao.save(entity);
	}

	public void update(Account entity) {
		accountDao.update(entity);
	}

	public void delete(int id) {
		accountDao.delete(id);
	}

	public List<Account> getAll() {
		return accountDao.getAll();
	}

}
