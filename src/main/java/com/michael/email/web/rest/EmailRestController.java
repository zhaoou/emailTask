package com.michael.email.web.rest;


import com.michael.email.core.email.Email;
import com.michael.email.core.user.User;
import com.michael.email.core.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/rest/v1/emails")
public class EmailRestController {

    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public Set<Email> sentEmails(@PathVariable("id") String id){
       // Set<Email> a = userService.findById(id).getSentEmails();
        return userService.findById(id).getSentEmails();
    }
}
