package com.agharibi.services;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.agharibi.config.JpaIntegrationConfig;
import com.agharibi.domain.Customer;
import com.agharibi.domain.User;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(JpaIntegrationConfig.class)
@ActiveProfiles("jpadao")
public class CustomerServiceJpaDaoImplTest {

	private CustomerService customerService;

	@Autowired
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@Test
	public void testListCustomers() throws Exception {
		List<Customer> customers = customerService.listAllCustomers();
		assert customers.size() == 5;
	}
	
	@Test
	public void testSaveWithUser() throws Exception {
		Customer customer = new Customer();
		User user = new User();
		
		user.setUsername("Daniella");
		user.setPassword("password");
		customer.setUser(user);
		
		Customer savedCustomer = customerService.addOrUpdateCutomer(customer);
		assert savedCustomer.getUser().getId() != null;
		
	}
	

}
