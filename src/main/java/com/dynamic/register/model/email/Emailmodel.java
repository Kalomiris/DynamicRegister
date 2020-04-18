package com.dynamic.register.model.email;

import javax.validation.constraints.Email;

public class Emailmodel {

    @Email
    private String email;

    private String subject;

    private String text;

    private String password;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }

    public String getPassword() {
        return password;
    }
}
