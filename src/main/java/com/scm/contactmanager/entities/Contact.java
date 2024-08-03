package com.scm.contactmanager.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Contact {

    @Id
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String picture;
    @Column(length = 1000)
    private String description;
    private boolean favourite = false;
    @Column(length = 1000)
    private String websiteLine;
    @Column(length = 1000)
    private String linkedInLink;


    //contact will have only one user 
    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "contact" ,cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
    private List<SocialLinks> socialLinks = new ArrayList<>();


}
