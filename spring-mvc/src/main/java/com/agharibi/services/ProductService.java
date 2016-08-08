package com.agharibi.services;

import java.util.List;

import com.agharibi.domain.Product;

public interface ProductService {
	
	List<Product> listAllProducts();
	Product getProductById(Integer id);
	Product saveOrUpdate(Product product);
	void deleteProduct(Integer id);
}
