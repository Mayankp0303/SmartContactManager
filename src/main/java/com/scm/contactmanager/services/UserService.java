package com.scm.contactmanager.services;

import java.util.List;
import java.util.Optional;

import com.scm.contactmanager.entities.User;

public interface UserService {

    User savUser(User user);

    Optional<User> getUserById(String id);
    Optional<User> updateUser(User user);

    void deleteUser(String userid);
    boolean isUserExist(String userid);
    boolean isUserExistByEmailId(String email);

    List<User> getAllUser();

    
}
