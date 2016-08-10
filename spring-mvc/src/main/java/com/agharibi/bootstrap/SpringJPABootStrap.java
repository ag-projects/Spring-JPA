package com.agharibi.bootstrap;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.agharibi.domain.Customer;
import com.agharibi.domain.Product;
import com.agharibi.services.CustomerService;
import com.agharibi.services.ProductService;

@Component
public class SpringJPABootStrap implements ApplicationListener<ContextRefreshedEvent> {

	private ProductService productService;
	private CustomerService customerService;

	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@Autowired
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		loadProducts();
		loadCustomers();
	}

	private void loadProducts() {

		Product p1 = new Product();
		p1.setId(1);
		p1.setDescription("Product 1");
		p1.setPrice(new BigDecimal("12.99"));
		p1.setImageUrl("http://example.com/product1");
		productService.saveOrUpdate(p1);

		Product p2 = new Product();
		p2.setId(2);
		p2.setDescription("Product 2");
		p2.setPrice(new BigDecimal("14.99"));
		p2.setImageUrl("http://example.com/product2");
		productService.saveOrUpdate(p2);

		Product p3 = new Product();
		p3.setId(3);
		p3.setDescription("Product 3");
		p3.setPrice(new BigDecimal("34.99"));
		p3.setImageUrl("http://example.com/product3");
		productService.saveOrUpdate(p3);

		Product p4 = new Product();
		p4.setId(4);
		p4.setDescription("Product 4");
		p4.setPrice(new BigDecimal("44.99"));
		p4.setImageUrl("http://example.com/product4");
		productService.saveOrUpdate(p4);

		Product p5 = new Product();
		p5.setId(5);
		p5.setDescription("Product 5");
		p5.setPrice(new BigDecimal("25.99"));
		p5.setImageUrl("http://example.com/product5");
		productService.saveOrUpdate(p5);

	}

	private void loadCustomers() {
		Customer customer1 = new Customer();
		customer1.setId(1);
		customer1.setFirstName("Leo");
		customer1.setLastName("Messi");
		customer1.setEmail("messi@yahoo.com");
		customer1.setPhone("(858) 600-2002");
		customerService.addOrUpdateCutomer(customer1);

		Customer customer2 = new Customer();
		customer2.setId(2);
		customer2.setFirstName("Luiz");
		customer2.setLastName("Suarez");
		customer2.setEmail("luiz@gmail.com");
		customer2.setPhone("(760) 450-3320");
		customerService.addOrUpdateCutomer(customer2);

		Customer customer3 = new Customer();
		customer3.setId(3);
		customer3.setFirstName("Luigi");
		customer3.setLastName("Buffon");
		customer3.setEmail("buffon@gmail.com");
		customer3.setPhone("(619) 540-6556");
		customerService.addOrUpdateCutomer(customer3);

		Customer customer4 = new Customer();
		customer3.setId(4);
		customer3.setFirstName("David");
		customer3.setLastName("Villa");
		customer3.setEmail("d.villa@gmail.com");
		customer3.setPhone("(760) 230-6556");
		customerService.addOrUpdateCutomer(customer4);

		Customer customer5 = new Customer();
		customer3.setId(5);
		customer3.setFirstName("Serjio");
		customer3.setLastName("Bajjo");
		customer3.setEmail("serjio@gmail.com");
		customer3.setPhone("(626) 840-6006");
		customerService.addOrUpdateCutomer(customer5);

	}

}
