package com.example.demo.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.LoginInfo;

public interface LoginData extends MongoRepository<LoginInfo, String>{

}
