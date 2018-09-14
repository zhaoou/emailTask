package com.michael.email.web.rest;


import com.michael.email.core.email.Email;
import com.michael.email.core.user.User;
import com.michael.email.core.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/rest/v1/emails")
public class EmailRestController {

    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public List<Email> sentEmails(@PathVariable("id") String id){
        List<Email> emails = userService.findById(id).getSentEmails();
        //emails = emails.stream().sorted(Comparator.comparing(Email::getCreatedTime)).collect(Collectors.toList());

        //emails = Stream.of(emails).filter()
        //  List<Email> newEmails = new ArrayList<Email>();
        for(int i=0;i<emails.size();i++){
            long time = emails.get(i).getCreatedTime();
            int count = (int) emails.stream().filter(e-> (e.getCreatedTime()==time)).count();//count same minute's email
            emails.get(i).setCountInSameMinute(count);
        }

        // filter by createTime
        emails =  emails.stream().collect(
                Collectors.collectingAndThen(Collectors.toCollection(
                        () -> new TreeSet<>(Comparator.comparing(o -> o.createdTime))), ArrayList::new)
        );
        //sort by createTime
        emails.stream().sorted(Comparator.comparing(Email::getCreatedTime));

        return emails;
    }
}
