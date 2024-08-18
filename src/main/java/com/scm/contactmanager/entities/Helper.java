package com.scm.contactmanager.entities;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;

public class Helper {

    public static String geEmailOfLoggedInUser(Authentication authentication){

        String username="";
        if(authentication instanceof OAuth2AccessToken){

            OAuth2AuthenticationToken token = (OAuth2AuthenticationToken)authentication;

            String clientName =token.getAuthorizedClientRegistrationId();

        }
        else{
            username = authentication.getName();    //gets the username in case of login
        }

        return username;
    }
}
