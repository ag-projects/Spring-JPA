package com.agharibi.controllers;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.agharibi.domain.Customer;
import com.agharibi.services.CustomerService;

public class CutomerControllerTest {

	private MockMvc mockMvc;

	@Mock
	private CustomerService customerService;

	@InjectMocks
	private CustomerController customerController;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
	}

	@Test
	public void testListCustomers() throws Exception {
		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer());
		customers.add(new Customer());

		when(customerService.listAllCustomers()).thenReturn((List) customers);

		mockMvc.perform(get("/customer/list"))
			.andExpect(status().isOk())
			.andExpect(model().attribute("customers", hasSize(2)))
			.andExpect(view().name("customer/list"));
	}

	@Test
	public void testShowCustomers() throws Exception {
		Integer id = 1;
		when(customerService.getCustomerBy(id)).thenReturn(new Customer());

		mockMvc.perform(get("/customer/show/1"))
			.andExpect(status().isOk())
			.andExpect(view().name("customer/show"))
			.andExpect(model().attribute("customer", instanceOf(Customer.class)));
	}

	@Test
	public void testEditCustomer() throws Exception {
		Integer id = 123;
		when(customerService.getCustomerBy(id)).thenReturn(new Customer());

		mockMvc.perform(get("/customer/show/edit/123"))
			.andExpect(status().isOk())
			.andExpect(view().name("customer/customerForm"))
			.andExpect(model().attribute("customer", instanceOf(Customer.class)));
	}

	@Test
	public void testNewCustomer() throws Exception {

		verifyZeroInteractions(customerService);
		mockMvc.perform(get("/customer/show/add"))
			.andExpect(status().isOk())
			.andExpect(view().name("customer/customerForm"))
			.andExpect(model().attribute("customer", instanceOf(Customer.class)));
	}

	@Test
	public void testSaveOrUpdateCustomer() throws Exception {
		String firstName = "Daniella";
		String lastName = "Peterson";
		String email = "dani@yahoo.com";
		String phone = "858-620-4554";
		Integer id = 1;

		Customer returnCustomer = new Customer();

		returnCustomer.setId(id);
		returnCustomer.setFirstName(firstName);
		returnCustomer.setLastName(lastName);
		returnCustomer.setEmail(email);
		returnCustomer.setPhone(phone);

		when(customerService.addOrUpdateCutomer(Matchers.<Customer>any())).thenReturn(returnCustomer);

		mockMvc.perform(post("/customer/show")
				.param("firstName", "Daniella")
				.param("lastName", "Peterson")
				.param("email", "dani@yahoo.com")
				.param("phone", "858-620-4554").param("id", "1"))
					.andExpect(status().is3xxRedirection())
					.andExpect(view().name("redirect:/customer/show/1"))
					.andExpect(model().attribute("customer", instanceOf(Customer.class)))
					.andExpect(model().attribute("customer", hasProperty("id", is(id))))
					.andExpect(model().attribute("customer", hasProperty("firstName", is(firstName))))
					.andExpect(model().attribute("customer", hasProperty("lastName", is(lastName))))
					.andExpect(model().attribute("customer", hasProperty("email", is(email))))
					.andExpect(model().attribute("customer", hasProperty("phone", is(phone))));

	}

	@Test
	public void testDeleteCustomer() throws Exception {
		Integer id = 1;
		mockMvc.perform(get("/customer/show/delete/1")).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/customer/list"));

		verify(customerService, times(1)).deleteCustomer(id);
	}

}
