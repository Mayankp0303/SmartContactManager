package com.scm.contactmanager.entities;



import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private String userId;

    @Column(nullable = false ,unique = true)
    private String name;
    @Column(nullable = false ,unique = true)
    private String email;
    private String password;

    @Column(length = 1000)
    private String about;
    @Column(length = 1000)
    private String profilePic;
    private String phoneNumber;


    //information
    private boolean enabled;
    private boolean emailVerified;
    private boolean phoneVerified;

    private Providers provider;
    private String privateUserId;

    //cascading is because when we update contact then the user table should also be update means it shuld be aligned
    //mapped by will see user field in contacts class and map this user id there 
    @OneToMany(mappedBy = "user" ,cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
    private List<Contact> contacts = new ArrayList<>();



}
