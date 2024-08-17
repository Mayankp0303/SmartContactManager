package com.scm.contactmanager.config;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.scm.contactmanager.entities.AppConstants;
import com.scm.contactmanager.entities.Providers;
import com.scm.contactmanager.entities.User;
import com.scm.contactmanager.repositories.UserRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OauthSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(OauthSuccessHandler.class);

    @Autowired
    private UserRepository userRepo;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
                
                LOGGER.info("Inside OauthSuccessHandler for processing redirect " + request.getAuthType());
                
                DefaultOAuth2User oauthUser =(DefaultOAuth2User)authentication.getPrincipal();
                String email = oauthUser.getAttribute("email");
                String name = oauthUser.getAttribute("name");
                String picture = oauthUser.getAttribute("picture");

                User createOUthUser = new User();
                createOUthUser.setEmail(email);
                createOUthUser.setEmailVerified(true);
                createOUthUser.setName(name);
                createOUthUser.setProfilePic(picture);
                createOUthUser.setUserId(UUID.randomUUID().toString());
                createOUthUser.setEnabled(true);
                createOUthUser.setProvider(Providers.GOOGLE);
                createOUthUser.setProviderUserId(oauthUser.getName());
                createOUthUser.setRoles(List.of(AppConstants.ROLE_USER));

                boolean checkCreate = userRepo.findByEmail(email).isPresent();
                if(!checkCreate){
                    userRepo.save(createOUthUser);
                }
                new DefaultRedirectStrategy().sendRedirect(request, response,"/user/profile");    
    }
    

}
