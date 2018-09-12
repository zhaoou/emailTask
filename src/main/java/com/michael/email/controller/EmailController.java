package com.michael.email.controller;


import com.michael.email.email.Email;
import com.michael.email.email.EmailService;
import com.michael.email.user.User;
import com.michael.email.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmailController {

    @Autowired
    EmailService emailService;

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String home(Model model) {
        if(userService.findAll().size()==0) {
            userService.creat(new User().setName("Michael"));
            userService.creat(new User().setName("Sagid"));
        }
        model.addAttribute("users", userService.findAll());

        return "login";//the name of html
    }


   // @PostMapping("/email/{id}")
    // public String findEmailsByUserId(@PathVariable("id") String id, Model model) {// userId

    @PostMapping("/email")
    public String findEmailsByUserId(@RequestParam(value="userId") String id, Model model) {// userId
        System.out.println("userId is "+id);
        model.addAttribute("fromEmails", userService.findById(id).getSentEmails());
        model.addAttribute("toEmails", emailService.findEmailsSentTo(userService.findById(id)));
        ////model.addAttribute("email", new Email(id,null,null));

        model.addAttribute("user", userService.findById(id));
        model.addAttribute("email", new Email().setSentByUser(userService.findById(id)));
        List<User> users= userService.findAll();
        users.remove(userService.findById(id));
        model.addAttribute("users", users);
        return "emails";//the name of html
    }

    //public String sendEmail( @ModelAttribute Email email, @RequestParam(value="userId") String userId ) {
    @PostMapping("/send")
    public String sendEmail( Model model, Email email) {
        emailService.addEmailToUser(email);
        emailService.creat(email);
        //model.addAttribute("userId",email.getSentByUser().getId());
        //return "redirect:/email/" + email.getSentByUser().getId() ;
        //return "redirect:/email" ;
        return "redirect:/" ;
    }

    @GetMapping("/delete/{id}")
    public String deleteEmail(@PathVariable("id") String id) {
        Email email = emailService.findById(id);
        emailService.deleteById(id);
        email.getSentByUser().getSentEmails().remove(email);
        return "redirect:/" ;
    }

//    //@GetMapping("/edit/{id}")
//    @PostMapping("/edit")
//    public String changeEmail(Email email) {
//        emailService.creat(email);
//        return "redirect:/email";
//    }
}
