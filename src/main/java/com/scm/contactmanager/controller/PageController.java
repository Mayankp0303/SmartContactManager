package com.scm.contactmanager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.contactmanager.entities.User;
import com.scm.contactmanager.forms.UserForm;
import com.scm.contactmanager.helper.Message;
import com.scm.contactmanager.helper.MessageType;
import com.scm.contactmanager.services.UserService;

import jakarta.servlet.http.HttpSession;


@Controller
public class PageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PageController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/home")
    public String home(Model model) {
        model.addAttribute("name", "TEST value");
        return "home";
    }

    @RequestMapping("/login")
    public String home() {
        return "login";
    }

    @RequestMapping("/about")
    public String aboutPage() {
        return "about";
    }

    @RequestMapping("/services")
    public String service(Model model) {
        model.addAttribute("name", "TEST value");
        return "services";
    }

    @RequestMapping("/register")
    public String register(Model  model) {
        UserForm userForm = new UserForm();
      //  userForm.setName("TESt ANME");
        model.addAttribute("userForm", userForm);
        return "register";
    }


    //@PostMapping(value="/do-register")        
    @PostMapping("/do-register")
    public String processRegister(@ModelAttribute UserForm userForm ,HttpSession session){
        System.out.println("Processing Registration" +userForm);

        //Commented builder as it was not storing default values in table
                // User user = User.builder().email(userForm.getEmail()).name(userForm.getName()).about(userForm.getAbout())
        // .password(userForm.getPassword()).phoneNumber(userForm.getPhoneNumber()).build();

        User user = new User();
        user.setAbout(userForm.getAbout());
        user.setName(userForm.getName());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setPassword(userForm.getPassword());
        user.setEmail(userForm.getEmail());
         
        userService.savUser(user);

        //here we are returning alert to the view of registration successfull
        Message message = Message.builder().content("Registration SucessFull!!").type(MessageType.blue).build();
        session.setAttribute("message", message);
        
        return "redirect:/register";
    }
}
