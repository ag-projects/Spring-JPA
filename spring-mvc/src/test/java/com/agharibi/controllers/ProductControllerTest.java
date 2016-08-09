package com.agharibi.controllers;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.agharibi.domain.Product;
import com.agharibi.services.ProductService;

public class ProductControllerTest {

	private MockMvc mockMvc;
	
	@Mock // Mockito Mock Object
	private ProductService productService;
	
	@InjectMocks  // setup the controller, and inject mock objects inot it.
	private ProductController productController;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this); // Initializes controller and the mocks
		mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
	}
	
	@Test
	public void testList() throws Exception {
		List<Product> products = new ArrayList<>();
		products.add(new Product());
		products.add(new Product());
		
		// Specific Mockito interaction, tell stub to return list of products
		// when productService.listAllProducts is envoked, the return a -> List<Product> products
		 when(productService.listAllProducts()).thenReturn((List) products);
		
		mockMvc.perform(get("/products/list"))
			.andExpect(status().isOk())
			.andExpect(view().name("products/list"))
			.andExpect(model().attribute("products", hasSize(2)));
	}
	
	@Test
	public void testShow() throws Exception {
		// Create a fake id;
		Integer id = 1;
		
		// Tell Mockito stub to return a new product for id = 1;
		// When productSerivce.getProduct is invoked, return a new product with the given id;
		when(productService.getProductById(id)).thenReturn(new Product());
		
		mockMvc.perform(get("/products/show/1"))
			.andExpect(status().isOk())
			.andExpect(view().name("products/show"))
			.andExpect(model().attribute("product", instanceOf(Product.class)));
	}
	
	@Test
	public void testEdit() throws Exception {
		Integer id = 1;
		when(productService.getProductById(id)).thenReturn(new Product());
		
		mockMvc.perform(get("/products/show/edit/1"))
			.andExpect(status().isOk())
			.andExpect(view().name("products/productForm"))
			.andExpect(model().attribute("product", instanceOf(Product.class)));
	}
	
	@Test
	public void testNewProduct() throws Exception {
		verifyZeroInteractions(productService);
		
		mockMvc.perform(get("/products/show/new"))
			.andExpect(status().isOk())
			.andExpect(view().name("products/productForm"))
			.andExpect(model().attribute("product", instanceOf(Product.class)));
	}
	
	@Test
	public void testSaveOrUpdate() throws Exception {
		Product returnProduct = new Product();
		
		Integer id = 1;
		String description = "Test description";
		BigDecimal price = new BigDecimal("12.99");
		String imageUrl = "exmaple.com/newProduct";
		
		returnProduct.setId(id);
		returnProduct.setDescription(description);
		returnProduct.setPrice(price);
		returnProduct.setImageUrl(imageUrl);
		
		when(productService.saveOrUpdate(Matchers.<Product>any())).thenReturn(returnProduct);
		
		// The below code verifies the returned item out of the post request.
		mockMvc.perform(post("/products/show")
				.param("id", "1")
				.param("description", "Test description")
				.param("price", "12.99")
				.param("imageUrl", "exmaple.com/newProduct"))
					.andExpect(status().is3xxRedirection())
					.andExpect(view().name("redirect:/products/show/1"))
					.andExpect(model().attribute("product", instanceOf(Product.class))) // verify if the product has been binded to the model
					.andExpect(model().attribute("product", hasProperty("id", is(id))))  // verify if each property is binded correctly/selected values
					.andExpect(model().attribute("product", hasProperty("description", is(description))))
					.andExpect(model().attribute("product", hasProperty("price", is(price))))
					.andExpect(model().attribute("product", hasProperty("imageUrl", is(imageUrl))));

		// the below code verifies what comes into the post request
		ArgumentCaptor<Product> boundProduct = ArgumentCaptor.forClass(Product.class);
		verify(productService).saveOrUpdate(boundProduct.capture());
					
		assertEquals(id, boundProduct.getValue().getId());
		assertEquals(description, boundProduct.getValue().getDescription());
		assertEquals(price, boundProduct.getValue().getPrice());
		assertEquals(imageUrl, boundProduct.getValue().getImageUrl());
	}
	
	@Test
	public void testDelete() throws Exception {

		Integer id = 1;
		mockMvc.perform(get("/products/show/delete/1"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/products/list"));
			
		verify(productService, times(1)).deleteProduct(id);
	}
	

}
