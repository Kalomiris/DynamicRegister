package com.dynamic.register.entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
public class UserCredential {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String userName;

    private String password;

    private boolean firstLogin = true;

    @OneToOne(mappedBy = "password")
    private UserDetails userDetails;

}
