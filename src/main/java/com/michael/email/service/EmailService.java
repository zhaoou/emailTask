package com.michael.email.service;

import java.util.List;
import java.util.Optional;

import com.michael.email.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.michael.email.model.Email;
import com.michael.email.persistense.EmailRepository;

@Service
public class EmailService {
	
	@Autowired
	EmailRepository emailRepo;
	
	public List<Email> findAll() {
		return emailRepo.findAll();
	}
	
	public Email save(Email e) {
		//Assert.notNull(q, "Quote cannot be null");
		//Assert.notNull(q.getText(), "Quote content cannot be null");
		return emailRepo.saveAndFlush(e);
		
	}
	
	public void deleteById(String id) {
		 emailRepo.deleteById(id);
	}
	
	public Optional<Email> findById(String id) {
		
		return emailRepo.findById(id);

	}

	public List<Email> findFromEmailsByUserId(String id) {

		return emailRepo.findAllByFromId(id);

	}

	public List<Email> findToEmailByUserId(String id) {

		return emailRepo.findAllByToId(id);

	}

	public Email addEmailToUser(Optional<User> u, Email e){
		Assert.notNull(u, "user cannot be null");
		Assert.notNull(e, "email cannot be null");
		Assert.notNull(u.get().getId(), "user must have persistent id");
		Assert.notNull(e.getId(), "email must have persistent id");
		u.get().getEmails().add(e);
		e.setUser(u.get());
		return emailRepo.saveAndFlush(e);
	}
}
