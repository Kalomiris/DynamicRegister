package com.dynamic.register.service;

import com.dynamic.register.entity.UserCredential;
import com.dynamic.register.model.user.CredentialModel;
import com.dynamic.register.repository.SaveCredentialRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CredentialServiceImpl implements CredentialService {


    @Autowired
    private SaveCredentialRepo saveCredentialRepo;


    public void saveCredentials (CredentialModel credentials){
        UserCredential userCredential = new UserCredential();
        userCredential.setUserName(credentials.getUserName());
        userCredential.setPassword(credentials.getPassword());
        saveCredentialRepo.save(userCredential);

    }

//    @Override
//    public boolean checkForRegisterUser(String password) {
//
//
//        return false;
//    }return
}
