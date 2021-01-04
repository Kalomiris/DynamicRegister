package com.dynamic.register.model.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class CredentialModel {

    private String userName;
    private String password;
    private boolean firstLogin;

}
