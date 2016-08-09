package com.agharibi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.agharibi.domain.Customer;
import com.agharibi.services.CustomerService;

@Controller
public class CustomerController {
	
	private CustomerService customerService;

	@Autowired
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@RequestMapping("/customer/list")
	public String listCustomers(Model model) {
		model.addAttribute("customers", customerService.listAllCustomers());
		return "customer/list";
	}
	
	@RequestMapping("/customer/show/{id}")
	public String getCutomerBy(@PathVariable Integer id, Model model) {
		model.addAttribute("customer", customerService.getCustomerBy(id));
		return "customer/show";
	}
	
	@RequestMapping("/customer/show/add")
	public String newCutomer(Model model) {
		model.addAttribute("customer", new Customer());
		return "customer/customerForm";
	}
	
	@RequestMapping(value = "/customer/show", method = RequestMethod.POST)
	public String addOrUpadateCustomer(Customer customer) {
		Customer addedCutomer = customerService.addOrUpdateCutomer(customer);
		return "redirect:/customer/show/" + addedCutomer.getId();
	}
	
	@RequestMapping("/customer/show/edit/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		model.addAttribute("customer", customerService.getCustomerBy(id));
		return "customer/customerForm";
	}
    
	@RequestMapping("/customer/show/delete/{id}")
	public String delete(@PathVariable Integer id) {
		customerService.deleteCustomer(id);
		return "redirect:/customer/list";
	}
	 	
}
