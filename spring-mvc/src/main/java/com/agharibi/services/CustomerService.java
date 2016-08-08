package com.agharibi.services;

import java.util.List;

import com.agharibi.domain.Customer;

public interface CustomerService {
	
	List<Customer> listAllCustomers();
	Customer getCustomerBy(Integer id);
	Customer addOrUpdateCutomer(Customer customer);
	void deleteCustomer(Integer id);

}
