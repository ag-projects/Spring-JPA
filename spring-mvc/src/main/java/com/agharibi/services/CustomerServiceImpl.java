package com.agharibi.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.RuntimeErrorException;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.agharibi.domain.Customer;

@Service
@Profile("map")
public class CustomerServiceImpl implements CustomerService {

	private Map<Integer, Customer> customers;

	public CustomerServiceImpl() {
		loadCustomers();
	}

	private void loadCustomers() {
		customers = new HashMap<>();

		Customer customer1 = new Customer();
		customer1.setId(1);
		customer1.setFirstName("Leonl");
		customer1.setLastName("Messi");
		customer1.setEmail("messi@yahoo.com");
		customer1.setPhone("(858) 600-2002");
	
		customers.put(1, customer1);

		Customer customer2 = new Customer();
		customer2.setId(2);
		customer2.setFirstName("Luiz");
		customer2.setLastName("Suarez");
		customer2.setEmail("luiz@gmail.com");
		customer2.setPhone("(760) 450-3320");
	
		customers.put(2, customer2);

		Customer customer3 = new Customer();
		customer3.setId(3);
		customer3.setFirstName("Luigi");
		customer3.setLastName("Buffon");
		customer3.setEmail("buffon@gmail.com");
		customer3.setPhone("(619) 540-6556");
	
		customers.put(3, customer3);

	}

	@Override
	public Customer addOrUpdateCutomer(Customer customer) {
		if(customer != null) {
			if(customer.getId() == null) {
				customer.setId(setNextKey());
			}
			customers.put(customer.getId(), customer);
			return customer;
		}
		else {
			throw new RuntimeException("Cannot add a customer with invalid id");
		}

	}

	private Integer setNextKey() {
		return Collections.max(customers.keySet()) + 1;
	}

	@Override
	public void deleteCustomer(Integer id) {
		customers.remove(id);
	}

	@Override
	public Customer getCustomerBy(Integer id) {
		return customers.get(id);
	}

	@Override
	public List<Customer> listAllCustomers() {
		return new ArrayList<>(customers.values());
	}





}
