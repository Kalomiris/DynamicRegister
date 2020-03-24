package com.dynamic.register.repository;

import com.dynamic.register.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LoginRepo extends JpaRepository<Login, Long> {

}
