package com.dynamic.register.entity;

import javax.persistence.*;

@Entity
public class UserCredential {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String userName;

    private String password;

    private boolean firstLongin = true;

    @OneToOne(mappedBy = "password")
    private UserDetails userDetails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public boolean isFirstLongin() {
        return firstLongin;
    }

    public void setFirstLongin(boolean firstLongin) {
        this.firstLongin = firstLongin;
    }
}
