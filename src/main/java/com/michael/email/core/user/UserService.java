package com.michael.email.core.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepo;
	
	public List<User> findAll() {
		return userRepo.findAll();
	}
	
	public User creat(User u) {
		Assert.notNull(u, "user cannot be null");
		Assert.notNull(u.getName(),"userName content cannot be null");
		return userRepo.saveAndFlush(u);
		
	}
	
	public void deleteById(String id) {
		userRepo.deleteById(id);
	}
	
	public User findById(String id) {
		
		return userRepo.findById(id).get();
		 
		//return userRepo.findOne(id);
	}
}
