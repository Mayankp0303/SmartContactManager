package com.scm.contactmanager.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@RequestMapping("/user")

public class UserController {

    

    @GetMapping("/dashboard")
    public String userDashBoard() {
        return "user/dashboard";
    }

    @RequestMapping(value = "/profile", method=RequestMethod.GET)
    public String profile(Model model,Authentication authentication) {

        return "user/profile";
    }
    
}
