package com.dynamic.register.service;

import com.dynamic.register.entity.UserCredential;
import com.dynamic.register.model.user.CredentialModel;
import com.dynamic.register.repository.SaveCredentialRepo;
import org.springframework.stereotype.Service;

@Service
public class CredentialServiceImpl implements CredentialService {


    private final SaveCredentialRepo saveCredentialRepo;

    public CredentialServiceImpl(SaveCredentialRepo saveCredentialRepo) {
        this.saveCredentialRepo = saveCredentialRepo;
    }


    public void saveCredentials(CredentialModel credentials) {
        UserCredential userCredential = new UserCredential();
        userCredential.setUserName(credentials.getUserName());
        userCredential.setPassword(credentials.getPassword());
        saveCredentialRepo.save(userCredential);

    }

    @Override
    public CredentialModel checkCredential(CredentialModel credentials) throws Exception {
        UserCredential checkedCredential = saveCredentialRepo.
                findUserCredentialByUserNameContainingAndPasswordContaining(credentials.getUserName(),
                        credentials.getPassword());
        if (checkedCredential == null) {
            return null;
        }
        CredentialModel credentialModel = new CredentialModel();
        credentialModel.setFirstLogin(checkedCredential.isFirstLogin());

        return credentialModel;
    }
}
