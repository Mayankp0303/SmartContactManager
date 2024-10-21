package com.scm.contactmanager.controller;


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
            session.setAttribute("message", Message.builder().content("Form has some errors ").type(MessageType.red).build());
            return "user/add_contact";
        }
        //get email of logged in user from the auth 
        String email = Helper.getEmailOfLoggedInUser(authentication);

        //get user details from the email of the logged in user  of mappinmg
        User user =userService.getUserByEmailId(email).get();


        //Process image 
        LOGGER.info("file is coming " + contactform.getPicture().getOriginalFilename());


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
        contactService.save(contact);
        System.out.println(contactform);

        session.setAttribute("message", Message.builder().content("New Contact has been added").type(MessageType.green).build());
        return "redirect:/user/contact/add";
    }
    
    

    
}   
