package com.scm.contactmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/contact")
public class ContactController {

    @GetMapping(path = "/add")
    public String addContactView(){
        return "user/add_contact";
    }
}   
