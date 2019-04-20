package com.wallet_ui.interfaces;

import java.util.List;

import com.wallet_ui.model.Customer;

public interface ICustomer {
	List<Customer> getListCustomer();
	Customer getById(int customerNumber);
	Customer saveCustomer(Customer customer);
	Customer updateCustomer(Customer customer);
	Customer delete(Customer customer);
	Customer getUserLogin(String username, String password);
}
