package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.LoginData;
import com.example.demo.model.LoginInfo;

@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	LoginData data;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<LoginInfo> info = data.findById(username);
		
		
		return info.map(UserInfoService::new).orElseThrow(()->new UsernameNotFoundException("Invaild User"));
	}

}
