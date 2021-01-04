package com.dynamic.register.model.email;

import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class EmailModel {

    @Email
    private String email;
    private String subject;
    private String text;
    private String password;

}