package com.scm.contactmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.contactmanager.forms.ContactForm;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/user/contact")
public class ContactController {

    @GetMapping(path = "/add")
    public String addContactView(Model model){

        ContactForm contactform = new ContactForm();
        model.addAttribute("contactform", contactform);
        return "user/add_contact";
    }

    @RequestMapping(value ="/add", method=RequestMethod.POST)
    public String saveContact(@ModelAttribute ContactForm contactForm) {
        
        System.out.println(contactForm);
        return "redirect:/user/contact/add";
    }
    
    

    
}   
