package com.agharibi.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.agharibi.domain.User;
import com.agharibi.security.EncryptionService;
import com.agharibi.services.AbstractJpaDaoService;
import com.agharibi.services.UserService;

@Service
@Profile("jpadao")
public class UserServiceJpaDaoImpl extends AbstractJpaDaoService implements UserService {

	private EncryptionService encryptionService;
	
	@Autowired
	public void setEncryptionService(EncryptionService encryptionService) {
		this.encryptionService = encryptionService;
	}

	@Override
	public List<?> listAll() {
		EntityManager em = emf.createEntityManager();
		return em.createQuery("from User", User.class).getResultList();
	}

	@Override
	public User getBy(Integer id) {
		EntityManager em = emf.createEntityManager();
		return em.find(User.class, id);
	}

	@Override
	public User saveOrUpdate(User user) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		if(user.getPassword() != null) {
			user.setEncryptedPassword(encryptionService.encryptString(user.getPassword()));
		}
		
		User savedUser = em.merge(user);
		em.getTransaction().commit();
		return savedUser;
	}

	@Override
	public void delete(Integer id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.find(User.class, id));
		em.getTransaction().commit();;
	}

}
