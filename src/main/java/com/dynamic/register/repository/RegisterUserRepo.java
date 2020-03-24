package com.dynamic.register.repository;

import com.dynamic.register.entity.DynamicRegisterUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterUserRepo extends JpaRepository<DynamicRegisterUser, Long> {

    DynamicRegisterUser findDynamicRegisterUserByPassword(String password);
}
