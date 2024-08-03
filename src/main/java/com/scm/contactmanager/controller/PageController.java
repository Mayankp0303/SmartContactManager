package com.scm.contactmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.contactmanager.forms.UserForm;


@Controller
public class PageController {

    @RequestMapping("/home")
    public String home(Model model) {
        model.addAttribute("name", "TEST value");
        return "home";
    }

    @RequestMapping("/about")
    public String aboutPage() {
        return "about";
    }

    @RequestMapping("/service")
    public String service(Model model) {
        model.addAttribute("name", "TEST value");
        return "services";
    }

    @RequestMapping("/register")
    public String register(Model  model) {
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        return "register";
    }


    //@PostMapping(value="/do-register")        
    @PostMapping("/do-register")
    public String processRegister(@ModelAttribute UserForm userForm ){
        System.out.println("Processing Registration");

        System.out.println("UserForm"+userForm);

        return "redirect:/register";
    }
}
