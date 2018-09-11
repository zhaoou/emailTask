package com.michael.email.emails;

import com.michael.email.model.User;
import com.michael.email.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.michael.email.model.Email;
import com.michael.email.service.EmailService;

import java.util.ArrayList;
import java.util.UUID;

@Controller
public class EmailController {
	
	@Autowired
	EmailService emailService;

	@Autowired
	UserService userService;
	
	@GetMapping("/")
	public String home(Model model) {
		//model.addAttribute("emails", service.findAll());
//		ArrayList<User> userList = new ArrayList<User>();
//		userList.add(new User(UUID.randomUUID().toString(),"Michael","zhaoouzhao@sezzwho.com"));
//		userList.add(new User(UUID.randomUUID().toString(),"Sagid","sagid@sezzwho.com"));

		userService.save(new User("Michael","zhaoouzhao@sezzwho.com"));
		userService.save(new User("Sagid","sagid@sezzwho.com"));
		model.addAttribute("users", userService.findAll());

		return "login";//the name of html
	}

	@GetMapping("/email/{id}")
	public String findEmailsByUserId(@PathVariable("id") String id, Model model) {//userId
		model.addAttribute("fromEmails", emailService.findFromEmailsByUserId(id));
		model.addAttribute("toEmails", emailService.findToEmailByUserId(id));
		model.addAttribute("email", new Email(id,null,null));
		return "emails";//the name of html
	}
	
	@PostMapping("/add")
	public String addEmailToUser(Email email) {
		//TODO email的id为什么过不来 ， form里怎么传多个对象，下拉框怎么放到form里传值
		emailService.addEmailToUser(userService.findById(email.getFromId()),email);
		return "redirect:/";
	}

//	@PostMapping("/add")
//	public String addEmailToUser(Email email) {
//		System.out.println("111");
//		return "redirect:/";
//	}
	
	@GetMapping("/delete/{id}")
	public String deleteEmail(@PathVariable("id") String id) {
		emailService.deleteById(id);
		return "redirect:/";
		//return "redirect:/tasks/";
	}
	
	
	@PostMapping("/edit")
	public String changeEmail(Email email) {
		emailService.save(email);
		return "redirect:/";
	}
	
	//@GetMapping("/edit/{id}")
	@RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
	public String editTask(@PathVariable("id") String id, Model model){
		model.addAttribute("email",emailService.findById(id));
		return "edit";
	}

}
