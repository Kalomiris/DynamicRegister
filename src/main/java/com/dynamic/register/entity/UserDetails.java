package com.dynamic.register.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String address;

    private String phone;

    private Date date;

    private byte[] pic;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserCredential password;

}
