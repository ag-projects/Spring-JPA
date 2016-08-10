package com.agharibi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.agharibi.domain.Product;
import com.agharibi.services.ProductService;

@Service
@Profile("jpadao")
public class ProductServiceJpaDaoImpl implements ProductService {

	private EntityManagerFactory emf;

	@PersistenceUnit
	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
	public List<Product> listAllProducts() {
		EntityManager em = emf.createEntityManager();	
		return em.createQuery("from Product", Product.class).getResultList();
	}

	@Override
	public Product getProductById(Integer id) {
		EntityManager em = emf.createEntityManager();
		return em.find(Product.class, id);
	}

	@Override
	public Product saveOrUpdate(Product product) {
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();		
		Product savedProduct = em.merge(product);
		em.getTransaction().commit();
		
		return savedProduct;
	}

	@Override
	public void deleteProduct(Integer id) {
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.remove(em.find(Product.class, id));
		em.getTransaction().commit();
	}

}
