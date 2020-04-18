package com.dynamic.register.data;

import java.util.Objects;

public class UserData {

    public UserData(String firstName, String password, String email) {
        this.firstName = firstName;
        this.password = password;
        this.email = email;
    }

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String phone;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserData userData = (UserData) o;
        return Objects.equals(firstName, userData.firstName) &&
                Objects.equals(lastName, userData.lastName) &&
                Objects.equals(email, userData.email) &&
                Objects.equals(password, userData.password) &&
                Objects.equals(phone, userData.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, password, phone);
    }
}
