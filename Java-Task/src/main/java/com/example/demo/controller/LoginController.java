package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.LoginData;
import com.example.demo.model.LoginInfo;

import com.example.demo.service.JwtService;
import com.example.demo.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("api/auth")
public class LoginController {
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	LoginData loginData;
	
	@Autowired
	UserService se;
	
	
	@PostMapping("/register")
	public String registerUser(@RequestBody LoginInfo loginBody) {
		loginBody.setPassword(encoder.encode(loginBody.getPassword()));
		loginBody.setRole("ROLE_ADMIN");
		
		loginData.save(loginBody);
		
		return "User Created Successfully";
	}
	
	@PostMapping("/login")
	public ResponseEntity<HashMap<Object, Object>> LoginToken(@RequestBody LoginInfo body) {
		List<Object> list = new ArrayList<>();
		HashMap<Object, Object> map = new HashMap<>();
		UserDetails loadUserByUsername = se.loadUserByUsername(body.getId());
		Optional<LoginInfo> userdata = loginData.findById(body.getId());
		if (loadUserByUsername.getUsername().length() > 0 && loadUserByUsername.isAccountNonExpired()
				&& loadUserByUsername.isAccountNonLocked() && loadUserByUsername.isCredentialsNonExpired()) {
			if (encoder.matches(body.getPassword(), loadUserByUsername.getPassword())) {
				list.add(userdata.get().getId());
				list.add(userdata.get().getRole());
				map.put("id", userdata.get().getId());
				map.put("role", userdata.get().getRole());
				map.put("token", new JwtService().gettoken(body.getId()));

				return ResponseEntity.ok(map);

			} else {
				throw new UsernameNotFoundException("Incorrect password");
			}
		} else {
			throw new UsernameNotFoundException("Invalid User");
		}
		
	}
	
	

}
