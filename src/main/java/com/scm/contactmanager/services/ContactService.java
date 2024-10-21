package com.scm.contactmanager.services;

import java.util.List;

import com.scm.contactmanager.entities.Contact;

public interface ContactService {
    
        Contact save(Contact contact);

        Contact updateContact(Contact contact);

        List<Contact> getALL();

        Contact getContactById(String id);

        void delete(String id);

        List<Contact> search(String name , String email ,String phoneNumber);

        List<Contact> getByUserId(String userid);
}   
