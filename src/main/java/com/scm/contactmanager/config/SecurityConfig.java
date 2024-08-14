package com.scm.contactmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(){

        //This doesnt use db it only uses in memory check
        UserDetails user1 = User.withDefaultPasswordEncoder().username("test").password("test").build();
        UserDetails user2 = User.withDefaultPasswordEncoder().username("test2").password("test2").build();
        return new InMemoryUserDetailsManager(user1,user2);
    }
}
