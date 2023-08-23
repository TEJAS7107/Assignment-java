package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class LoginInfo {
	@Id
	private String id;
	
	@Field
	private String password;
	
	@Field
	private String role;
	
	public LoginInfo() {
		// TODO Auto-generated constructor stub
	}
	
	
	public LoginInfo(String id, String password, String role) {
		super();
		this.id = id;
		this.password = password;
		this.role = role;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	
	
	
}
