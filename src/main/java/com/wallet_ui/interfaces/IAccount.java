package com.wallet_ui.interfaces;

import java.util.List;

import com.wallet_ui.model.Account;

public interface IAccount {
	List<Account> getListAccount();
	Account save(Account account);
	Account update(Account account);
	Account delete(Account accountNumber);
	List<Account> getAccountByCustomerNumber(int customerNumber);
}
