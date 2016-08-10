package com.agharibi.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.agharibi.domain.Product;

@Service
@Profile("map")
public class ProductServiceImpl implements ProductService {

	private Map<Integer, Product> products;

	public ProductServiceImpl() {
		loadProducts();
	}
	

	@Override
	public void deleteProduct(Integer id) {
		products.remove(id);
	}

	@Override
	public Product saveOrUpdate(Product product) {
		if(product != null) {
			if(product.getId() == null) {
				product.setId(getNextKey());
			}
			products.put(product.getId(), product);
			return product;
		}
		else {
			throw new RuntimeException("Product cannot be null");
		}
	}
	
	private Integer getNextKey() {
		return Collections.max(products.keySet()) + 1;
	}

	@Override
	public Product getProductById(Integer id) {
		return products.get(id);
	}

	@Override
	public List<Product> listAllProducts() {
		return new ArrayList<>(products.values());
	}

	private void loadProducts() {
		products = new HashMap<>();

		Product p1 = new Product();
		p1.setId(1);
		p1.setDescription("Product 1");
		p1.setPrice(new BigDecimal("12.99"));
		p1.setImageUrl("http://example.com/product1");

		products.put(1, p1);

		Product p2 = new Product();
		p2.setId(2);
		p2.setDescription("Product 2");
		p2.setPrice(new BigDecimal("14.99"));
		p2.setImageUrl("http://example.com/product2");

		products.put(2, p2);

		Product p3 = new Product();
		p3.setId(3);
		p3.setDescription("Product 3");
		p3.setPrice(new BigDecimal("34.99"));
		p3.setImageUrl("http://example.com/product3");

		products.put(3, p3);

		Product p4 = new Product();
		p4.setId(4);
		p4.setDescription("Product 4");
		p4.setPrice(new BigDecimal("44.99"));
		p4.setImageUrl("http://example.com/product4");

		products.put(4, p4);

		Product p5 = new Product();
		p5.setId(5);
		p5.setDescription("Product 5");
		p5.setPrice(new BigDecimal("25.99"));
		p5.setImageUrl("http://example.com/product5");

		products.put(5, p5);

	}

}
