package com.scm.contactmanager.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scm.contactmanager.entities.AppConstants;
import com.scm.contactmanager.entities.User;
import com.scm.contactmanager.helper.ResourceNotFoundExcepton;
import com.scm.contactmanager.repositories.UserRepository;
import com.scm.contactmanager.services.UserService;

@Service
public class UserServiceImpl implements UserService {
    private org.slf4j.Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passEncoder;

    @Override
    public User savUser(User user) {
        LOGGER.info(">> Inside save User");

        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);

        user.setPassword(passEncoder.encode(user.getPassword()));

        user.setRoles(List.of(AppConstants.ROLE_USER));
        return userRepository.save(user);
    }


    @Override
    public Optional<User> getUserById(String id) {
        
        LOGGER.info(">> Inside getUser by Id");
        return userRepository.findById(id);
    }


    @Override
    public Optional<User> updateUser(User user) {
        
        User user2=userRepository.findById(user.getUserId()).orElseThrow(()->new ResourceNotFoundExcepton("No User available with user id"));
        user2.setName(user.getName());
        user2.setEmail(user.getEmail());
        user2.setAbout(user.getAbout());
        user2.setPhoneNumber(user.getPhoneNumber());
        user2.setPassword(user.getPassword());
        user2.setProfilePic(user.getProfilePic());
        user2.setEnabled(user.isEnabled());
        user2.setEmailVerified(user.isEmailVerified());
        user2.setPhoneVerified(user.isPhoneVerified());
        user2.setProvider(user.getProvider());
        user2.setProviderUserId(user.getProviderUserId());

        User save =userRepository.save(user2);
        return Optional.ofNullable(save);
       
    }


    @Override
    public void deleteUser(String userid) {
        User user2=userRepository.findById(userid).orElseThrow(()->new ResourceNotFoundExcepton("No User available with user id"));
        userRepository.delete(user2);
    }


    @Override
    public boolean isUserExist(String userid) {
        
        User user2 = userRepository.findById(userid).orElse(null);
        return user2!=null ? true :false;
    }


    @Override
    public boolean isUserExistByEmailId(String email) {
        
        User user2 = userRepository.findByEmail(email).orElse(null);
        return user2!=null ? true :false;
        
    }


    @Override
    public List<User> getAllUser() {
        
        return userRepository.findAll();
    }


    @Override
    @Cacheable
    public Optional<User> getUserByEmailId(String email) {
        
        return Optional.of(userRepository.findByEmail(email).orElse(null));
    }


}
