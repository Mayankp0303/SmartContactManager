package com.scm.contactmanager.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Configuration
public class AppConfig {
    @Value("${cloudinary.secret.cloudName}")
    private String cloudName;

    @Value("${cloudinary.secret.apiKey}")
    private String cloudinaryApiKey;
    @Value("${cloudinary.secret.apiSecret}")
    private String apiSecret;


    @Bean
    public Cloudinary getCloudinary(){
        return new Cloudinary(ObjectUtils.asMap("cloud_name",cloudName,"api_key",cloudinaryApiKey,"api_secret",apiSecret));
    }
    
}
