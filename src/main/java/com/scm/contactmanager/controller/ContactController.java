package com.scm.contactmanager.controller;


import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.contactmanager.entities.Contact;
import com.scm.contactmanager.entities.Helper;
import com.scm.contactmanager.entities.User;
import com.scm.contactmanager.forms.ContactForm;
import com.scm.contactmanager.helper.Message;
import com.scm.contactmanager.helper.MessageType;
import com.scm.contactmanager.services.ContactService;
import com.scm.contactmanager.services.ImageService;
import com.scm.contactmanager.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
@RequestMapping("/user/contact")
public class ContactController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;

    @RequestMapping(path = "/add")
    public String addContactView(Model model){

        ContactForm contactform = new ContactForm();
       // contactform.setLinkedInLink("htpp.test.com");
        model.addAttribute("contactform", contactform);
       
        return "user/add_contact";
    }

    @RequestMapping(value ="/addNew", method=RequestMethod.POST)
    public String saveContact( @Valid @ModelAttribute ContactForm contactform, BindingResult bindingResult,Authentication authentication,HttpSession session) {
        
        if(bindingResult.hasErrors()){
            System.out.println(bindingResult);
            session.setAttribute("message", Message.builder().content("Form has some errors ").type(MessageType.red).build());
            return "user/add_contact";
        }
        //test email from session object 
        String testEmail = (String)session.getAttribute("userEmail");
        System.out.println("Testing email fro msession attribute" +testEmail);
        //get email of logged in user from the auth 
        String email = Helper.getEmailOfLoggedInUser(authentication);

        //get user details from the email of the logged in user  of mappinmg
        User user =userService.getUserByEmailId(email).get();


        //Process image 
        LOGGER.info("file is coming " + contactform.getPicture().getOriginalFilename());

        String filename = UUID.randomUUID().toString();
        String fileurl =imageService.uploadImage(contactform.getPicture(),filename);
        LOGGER.info("Check for file url " + fileurl);

        Contact contact = new Contact();
        contact.setFavourite(contactform.isFavourite());
        contact.setEmail(contactform.getEmail());
        contact.setName(contactform.getName());
        contact.setPhoneNumber(contactform.getPhoneNumber());
        contact.setAddress(contactform.getAddress());
        contact.setDescription(contactform.getDescription());
        contact.setUser(user);
        contact.setLinkedInLink(contactform.getLinkedInLink());
        contact.setWebsiteLink(contactform.getWebsiteLink());
        contact.setPicture(fileurl);
        //contactService.save(contact);
        System.out.println("Contact form data"+ contactform);

        contactService.save(contact);

        session.setAttribute("message", Message.builder().content("New Contact has been added").type(MessageType.green).build());
        return "redirect:/user/contact/add";
    }
    
    

    
}   
