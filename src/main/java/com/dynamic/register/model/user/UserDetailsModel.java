package com.dynamic.register.model.user;

import com.dynamic.register.wrapper.BaseModel;
import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class UserDetailsModel extends BaseModel {

    private long id;

    private String firstName;

    private String lastName;

    @Email(message = "is not an email")
    private String email;

    private String phone;

    private String address;

    private byte[] picByte;

}
