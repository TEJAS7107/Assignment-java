package com.example.demo.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.CreateUser;

public interface UserData extends MongoRepository<CreateUser, String> {
	
	void deleteByEmail(String email);
}
