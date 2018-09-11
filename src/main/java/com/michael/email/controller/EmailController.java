package com.michael.email.controller;


import com.michael.email.email.Email;
import com.michael.email.email.EmailService;
import com.michael.email.user.User;
import com.michael.email.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmailController {
	
	@Autowired
	EmailService emailService;

	@Autowired
	UserService userService;
	
	@GetMapping("/")
	public String home(Model model) {
		userService.creat(new User().setName("Michael"));
		userService.creat(new User().setName("Sagid"));
		model.addAttribute("users", userService.findAll());
		return "login";//the name of html
	}

	@GetMapping("/email/{id}")
	public String findEmailsByUserId(@PathVariable("id") String id, Model model) {// userId
		model.addAttribute("fromEmails", userService.findById(id).getSentEmails());
		model.addAttribute("toEmails", emailService.findEmailsSentTo(userService.findById(id)));
		////model.addAttribute("email", new Email(id,null,null));

		model.addAttribute("user", userService.findById(id));
		model.addAttribute("email", new Email().setSentByUser(userService.findById(id)));
		return "emails";//the name of html
	}
	
	@PostMapping("/send")
	public String sendEmail(Email email) {
		//TODO email的id为什么过不来 ， form里怎么传多个对象，下拉框怎么放到form里传值
		emailService.addEmailToUser(email);
		emailService.creat(email);
		return "redirect:/email/" + email.getSentByUser().getId() ;
	}
	
	@GetMapping("/delete/{id}")
	public String deleteEmail(@PathVariable("id") String id) {
		Email email = emailService.findById(id);
		emailService.deleteById(id);
		email.getSentByUser().getSentEmails().remove(email);
		return "redirect:/email/" + email.getSentByUser().getId() ;
	}

	//@GetMapping("/edit/{id}")
	@PostMapping("/edit")
	public String changeEmail(Email email) {
		emailService.creat(email);
		return "redirect:/emails";
	}
}
