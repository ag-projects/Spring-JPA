package com.agharibi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.agharibi.domain.Product;
import com.agharibi.services.ProductService;

@Controller
public class ProductController {

	private ProductService productService;

	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@RequestMapping("products/list")
	public String listProducts(Model model) {
		model.addAttribute("products", productService.listAllProducts());
		return "products/list";
	}
	
	@RequestMapping("/products/show/{id}")
	public String getProduct(@PathVariable Integer id, Model model) {
		model.addAttribute("product", productService.getProductById(id));	
		return "products/show";
	}
	
	@RequestMapping("/products/show/new")
	public String newProduct(Model model) {
		model.addAttribute("product", new Product());	
		return "products/productForm";
	}
	
	@RequestMapping(value = "/products/show", method = RequestMethod.POST)
	public String saveOrUpdate(Product product) {
		Product savedProduct = productService.saveOrUpdate(product);		
		return "redirect:/products/show/" + savedProduct.getId();  // which is -> "/product/{id}"
	}
	
	@RequestMapping("/products/show/edit/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		model.addAttribute("product", productService.getProductById(id));
		return "products/productForm";
	}
	
	@RequestMapping("/products/show/delete/{id}")
	public String delete(@PathVariable Integer id) {
		productService.deleteProduct(id);
		return "redirect:/products/list";
	}	
	
}
