package com.scm.contactmanager.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.contactmanager.entities.Contact;
import com.scm.contactmanager.helper.ResourceNotFoundExcepton;
import com.scm.contactmanager.repositories.ContactRepository;
import com.scm.contactmanager.services.ContactService;

@Service
public class ContactServiceImpl implements ContactService{

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public Contact save(Contact contact) {
        String contactid = UUID.randomUUID().toString();
        contact.setId(contactid);

        return contactRepository.save(contact);

    }

    @Override
    public Contact updateContact(Contact contact) {
       List<Contact> c = contactRepository.findByEmail(contact.getEmail());
        Contact elem = c.get(0);
        elem.setName(contact.getName());
        elem.setAddress(contact.getAddress());

       return contactRepository.save(elem);
    }

    @Override
    public List<Contact> getALL() {
      return contactRepository.findAll();
    }

    @Override
    public Contact getContactById(String id) {
       
        return contactRepository.findById(id).orElseThrow(()-> new ResourceNotFoundExcepton("Did notsee any contact with id " + id));
    }

    @Override
    public void delete(String id) {
         contactRepository.deleteById(id);
    }

    @Override
    public List<Contact> search(String name, String email, String phoneNumber) {
        
        if(!email.isEmpty()){
            return contactRepository.findByEmail(email);
        }
        else if(!name.isEmpty()){
            return contactRepository.findByName(name);
        }
        else {
            return contactRepository.findByPhoneNumber(phoneNumber);
        }
    }

    @Override
    public List<Contact> getByUserId(String userid) {
       return contactRepository.findByUserId(userid);
    }
    
}
