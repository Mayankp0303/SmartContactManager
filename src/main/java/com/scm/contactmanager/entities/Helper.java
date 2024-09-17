package com.scm.contactmanager.entities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

public class Helper {
    private static final Logger LOGGER = LoggerFactory.getLogger(Helper.class);

    public static String  getEmailOfLoggedInUser(Authentication authentication){

        String username="";
        if(authentication instanceof OAuth2AuthenticationToken){
            LOGGER.info("Login with Oauth Proceeded");
            OAuth2AuthenticationToken token = (OAuth2AuthenticationToken)authentication;

            String clientName =token.getAuthorizedClientRegistrationId();
            LOGGER.info("Login with Oauth Proceeded Client name is " + clientName);
            DefaultOAuth2User oauthUser =(DefaultOAuth2User)authentication.getPrincipal();
            if(clientName.equals("google")){
                LOGGER.info("OauthLogin with Google Provider");
                username= oauthUser.getAttribute("email");
            }
            else if(clientName.equals("github")){
                LOGGER.info("OauthLogin with Github Provider");
               username = oauthUser.getAttribute("email") !=null ?  oauthUser.getAttribute("email") :oauthUser.getAttribute("login")+"@gmail.com";
            }

        }
        else{
            username = authentication.getName();    //gets the username in case of login
        }

        return username;
    }
}
