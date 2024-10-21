package com.scm.contactmanager.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails{

    @Id
    private String userId;

    @Column(nullable = false )
    private String name;
    @Column(nullable = false ,unique = true)
    private String email;
    @Getter(AccessLevel.NONE)
    private String password;

    @Column(length = 1000)
    private String about;
    @Column(length = 1000)
    private String profilePic;
    private String phoneNumber;


    //information
    @Getter(AccessLevel.NONE)
    private boolean enabled;
    private boolean emailVerified;
    private boolean phoneVerified;

    private Providers provider;
    private String providerUserId;

    //cascading is because when we update contact then the user table should also be update means it shuld be aligned
    //mapped by will see user field in contacts class and map this user id there 
    @OneToMany(mappedBy = "user" ,cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
    private List<Contact> contacts = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER) // to check use
    List<String> roles = new ArrayList<>();
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //Collection of Granted Authorities [ROLES {ADMIN,USER}]
      Collection<SimpleGrantedAuthority> roles1=roles.stream().map(role->new SimpleGrantedAuthority(role)).collect(Collectors.toList());

      return roles1;
    }

    @Override
    public String getUsername() {
        return this.email;
        
    }

    @Override
    public String getPassword() {
       return this.password;
    }



}
