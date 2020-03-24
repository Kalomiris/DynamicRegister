package com.dynamic.register.wrapper;

import java.io.Serializable;

public class BaseParameters implements Serializable {

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
