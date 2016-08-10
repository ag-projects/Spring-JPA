package com.agharibi.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.persistence.Version;

@Entity
public class User implements DomainObject {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Version
	private Integer version;
	private String username;
	
	@Transient 
	private String password;
	private String encryptedPassword;
	private Boolean enabled = true;

	public Boolean getEnabled() {
		return enabled;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	@Override
	public Integer getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public Integer getVersion() {
		return version;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
