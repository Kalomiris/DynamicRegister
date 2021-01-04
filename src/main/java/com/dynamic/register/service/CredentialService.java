package com.dynamic.register.service;

import com.dynamic.register.model.user.CredentialModel;

public interface CredentialService {

    void saveCredentials (CredentialModel credentials);

    CredentialModel checkCredential(CredentialModel credentials) throws Exception;

}
