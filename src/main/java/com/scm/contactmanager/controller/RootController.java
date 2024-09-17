package com.scm.contactmanager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.scm.contactmanager.entities.Helper;
import com.scm.contactmanager.entities.User;
import com.scm.contactmanager.services.UserService;

@ControllerAdvice
public class RootController {

    @Autowired
    private UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(RootController.class);

    @ModelAttribute   // this will be fired before all methds below / services below 
    public void addLoggedInUserToProfile(Model model ,Authentication authentication){
        // if any user is loogen in we have the auth object 
        if(authentication==null){
            return ;
        }
       
        String email = Helper.getEmailOfLoggedInUser(authentication);
        LOGGER.info("Email of user "+ email);
        User user = userService.getUserByEmailId(email).get();
        model.addAttribute("loggedInUser", user);
        LOGGER.info("User email id " + user.getEmail());
    }
    
}
