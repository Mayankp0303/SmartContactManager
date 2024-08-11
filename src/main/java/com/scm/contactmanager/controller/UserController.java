package com.scm.contactmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/user")
public class UserController {


    @RequestMapping(value = "/dashboard", method=RequestMethod.GET)
    public String userDashBoard() {
        return "user/dashboard";
    }

    @RequestMapping(value = "/profile", method=RequestMethod.GET)
    public String profile() {
        return "user/dashboard";
    }
    
}
