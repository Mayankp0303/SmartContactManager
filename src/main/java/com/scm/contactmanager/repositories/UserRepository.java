package com.scm.contactmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scm.contactmanager.entities.User;


@Repository
public interface UserRepository extends JpaRepository<User,String>{

}
