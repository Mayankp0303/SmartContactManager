package com.scm.contactmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Configuration
public class AppConfig {

    private String cloudName;

    private String apiKey;

    private String apiSecret;


    @Bean
    public Cloudinary getCloudinary(){

        return new Cloudinary(ObjectUtils.asMap("cloud_name",cloudName,"apiKey",apiKey,"apiSecret",apiSecret));
    }
    
}
