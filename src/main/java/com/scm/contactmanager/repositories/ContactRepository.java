package com.scm.contactmanager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.scm.contactmanager.entities.Contact;
import com.scm.contactmanager.entities.User;

@Repository
public interface ContactRepository extends JpaRepository<Contact,String>{
    
    List<Contact>  findByUser(User user);

    @Query("select c from Contact c where c.user.id= :userId")
    List<Contact>  findByUserId(@Param ("userId") String userId);

    List<Contact> findByEmail(String email);
    List<Contact> findByName(String name);
    List<Contact> findByPhoneNumber(String phoneNumber);
}
