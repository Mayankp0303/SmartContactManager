package com.scm.contactmanager.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.scm.contactmanager.services.impl.SecurityCustomUserDetailService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class SecurityConfig {

    // @Bean
    // public UserDetailsService userDetailsService(){

    //     //This doesnt use db it only uses in memory check
    //     UserDetails user1 = User.withDefaultPasswordEncoder().username("test").password("test").build();
    //     UserDetails user2 = User.withDefaultPasswordEncoder().username("test2").password("test2").build();
    //     return new InMemoryUserDetailsManager(user1,user2);
    // }

    @Autowired
    private SecurityCustomUserDetailService userDetailService;

    @Autowired
    private OauthSuccessHandler oauthHandler;
    //related to login a
    @Bean
    public AuthenticationProvider authProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        daoAuthenticationProvider.setPasswordEncoder(encoder());

        return  daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }


    //All pages were secure so need to open 

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        //Authorize home signup nd all

        httpSecurity.authorizeHttpRequests(httprequest->{
          //  httprequest.requestMatchers("/home","register").permitAll();
            httprequest.requestMatchers("/user/**").authenticated();
            httprequest.anyRequest().permitAll();
        });
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.formLogin(form->{

            form.loginPage("/login");
            form.loginProcessingUrl("/authenticate");
            form.successForwardUrl("/user/dashboard");
            form.failureForwardUrl("/login?error=true");

            form.usernameParameter("email");
            form.passwordParameter("password");

            form.successHandler(new AuthenticationSuccessHandler() {
             @Override
             public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        // Custom logic for successful authentication
             response.sendRedirect("/user/dashboard");
         }
        });
        });

        httpSecurity.logout(logoutForm->{
            logoutForm.logoutUrl("/logout");
            logoutForm.logoutSuccessUrl("/login?logout=true");
            logoutForm.invalidateHttpSession(true);
            logoutForm.deleteCookies("JSESSIONID");
        });

        httpSecurity.oauth2Login(oauth->{
            oauth.loginPage("/login");
            oauth.successHandler(oauthHandler);
            
        });
        return httpSecurity.build();
    }
}
