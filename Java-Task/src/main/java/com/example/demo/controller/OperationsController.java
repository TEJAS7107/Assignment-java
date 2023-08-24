package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.UserData;
import com.example.demo.model.CreateUser;

@CrossOrigin("*")
@RestController
@RequestMapping("api/operations")
public class OperationsController {
	
	@Autowired
	UserData data;
	
	@PostMapping("/add")
	public String SaveUserDeatils(@RequestBody CreateUser user) {
		
		data.save(user);
		
		return "User details saved successfully";
	}
	
	@GetMapping("/read")
	public List<CreateUser> ReadData() {
		
		return data.findAll();
		
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteList(@PathVariable String id) {
		
		//data.deleteById(id);
		data.deleteByEmail(id);
		//return "deleted";
	}
	
	@PutMapping("/update/{id}")
	public void UpdateRecord(@PathVariable String id,@RequestBody CreateUser user) {
		Optional<CreateUser> list = data.findById(id);
		list.get().setFirstname(user.getFirstname());
		list.get().setLastname(user.getLastname());
		list.get().setEmail(user.getEmail());
		list.get().setCity(user.getCity());
//		list.get().setNumber(user.getNumber());
		list.get().setState(user.getState());
		list.get().setStreet(user.getStreet());
		list.get().setAddress(user.getAddress());
		
		
		
		data.save(list.get());
		
	}

}
