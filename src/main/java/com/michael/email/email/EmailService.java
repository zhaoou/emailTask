package com.michael.email.email;

import com.michael.email.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class EmailService {
	
	@Autowired
	EmailRepository emailRepo;
	
	public Email creat(Email e) {
		Assert.notNull(e, "Email cannot be null");
		Assert.notNull(e.getTitle(), "Email title content cannot be null");
		Assert.notNull(e.getContent(), "Email  content cannot be null");
		Assert.notNull(e.getSentByUser(), "Email sentByUser content cannot be null");
		//Assert.notNull(e.getToUser(), "Email toUser content cannot be null");
		return emailRepo.saveAndFlush(e);

	}
	
	public void deleteById(String id) {
		 emailRepo.deleteById(id);
	}
	
	public Email findById(String id) {
		
		return emailRepo.findById(id).get();

	}

	public  List<Email> findEmailsSentTo(User user){
		return  emailRepo.findByToUserId(user.getId());
	}

	public Email addEmailToUser(Email e){
		Email newEmail = emailRepo.saveAndFlush(e);
		User user =  newEmail.getSentByUser();
		user.getSentEmails().add(e);
		return newEmail;
	}


}
