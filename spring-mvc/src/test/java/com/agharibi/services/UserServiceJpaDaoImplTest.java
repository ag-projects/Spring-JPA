package com.agharibi.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.agharibi.config.JpaIntegrationConfig;
import com.agharibi.domain.User;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(JpaIntegrationConfig.class)
@ActiveProfiles("jpadao")
public class UserServiceJpaDaoImplTest {

	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Test
	public void testSaveUserData() throws Exception {
		User user  = new User();
		user.setUsername("Alexis");
		user.setPassword("password");
		
		User savedUser = userService.saveOrUpdate(user);
		
		assert savedUser.getId() != null;
		assert savedUser.getEncryptedPassword() != null;
		
		System.err.println("Encrypted password is -> " + savedUser.getEncryptedPassword());
		
	}



}
