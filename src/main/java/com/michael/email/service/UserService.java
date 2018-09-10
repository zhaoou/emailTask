package com.michael.email.service;

import com.michael.email.model.User;
import com.michael.email.persistense.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepo;
	
	public List<User> findAll() {
		return userRepo.findAll();
	}
	
	public User save(User u) {
		//Assert.notNull(q, "Quote cannot be null");
		//Assert.notNull(q.getText(), "Quote content cannot be null");
		return userRepo.saveAndFlush(u);
		
	}
	
	/*public void deleteById(Long id) {
		userRepo.deleteById(id);
	}
	
	public Optional<User> findById(Long id) {
		
		return userRepo.findById(id);
		 
		//return emailRepo.findOne(id);
	}*/
}
