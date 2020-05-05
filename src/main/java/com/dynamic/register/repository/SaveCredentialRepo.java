package com.dynamic.register.repository;

import com.dynamic.register.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SaveCredentialRepo extends JpaRepository<UserCredential, Long> {

    UserCredential findUserCredentialByUserNameContainingAndPasswordContaining(String userName, String password);

}
