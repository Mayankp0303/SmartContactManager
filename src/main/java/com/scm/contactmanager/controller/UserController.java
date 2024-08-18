package com.scm.contactmanager.controller;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.scm.contactmanager.entities.Helper;


@Controller
@RequestMapping("/user")
public class UserController {


    @RequestMapping(value = "/dashboard", method=RequestMethod.GET)
    public String userDashBoard() {
        return "user/dashboard";
    }

    @RequestMapping(value = "/profile", method=RequestMethod.GET)
    public String profile(Authentication authentication) {

        String email = Helper.geEmailOfLoggedInUser(authentication);
        return "user/profile";
    }
    
}
