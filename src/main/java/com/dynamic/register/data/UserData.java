package com.dynamic.register.data;

import java.util.Objects;

public class UserData {

    public UserData(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    private String userName;

    private String password;

    private String email;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserData userData = (UserData) o;
        return Objects.equals(userName, userData.userName) &&
                Objects.equals(password, userData.password) &&
                Objects.equals(email, userData.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, password, email);
    }
}
