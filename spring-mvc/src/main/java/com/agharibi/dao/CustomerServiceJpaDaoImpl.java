package com.agharibi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.agharibi.domain.Customer;
import com.agharibi.services.CustomerService;

@Service
@Profile("jpadao")
public class CustomerServiceJpaDaoImpl implements CustomerService{
	
	private EntityManagerFactory emf;

	@PersistenceUnit
	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
	public List<Customer> listAllCustomers() {
		EntityManager em = emf.createEntityManager();
		return em.createQuery("from Customer", Customer.class).getResultList();
		
	}

	@Override
	public Customer getCustomerBy(Integer id) {
		EntityManager em = emf.createEntityManager();
		return em.find(Customer.class, id);
	}

	@Override
	public Customer addOrUpdateCutomer(Customer customer) {
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		Customer savedCustomer = em.merge(customer);
		em.getTransaction().commit();
		
		return savedCustomer;
	}

	@Override
	public void deleteCustomer(Integer id) {
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.remove(em.find(Customer.class, id));
		em.getTransaction().commit();	
	}

}
