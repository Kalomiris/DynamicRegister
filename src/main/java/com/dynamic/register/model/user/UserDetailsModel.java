package com.dynamic.register.model.user;

import com.dynamic.register.wrapper.BaseModel;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class UserDetailsModel extends BaseModel {

    private long id;

    private String firstName;

    private String lastName;

    @Email(message = "is not an email")
    private String email;

    private String phone;

    private String address;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private byte[] picByte;

    public byte[] getPicByte() {
        return picByte;
    }

    public void setPicByte(byte[] picByte) {
        this.picByte = picByte;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



}
