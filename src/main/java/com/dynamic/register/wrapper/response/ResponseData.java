package com.dynamic.register.wrapper.response;


import org.springframework.http.HttpStatus;

public class ResponseData {

    private HttpStatus status;

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}