package com.dynamic.register.repository;

import com.dynamic.register.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepo extends JpaRepository<UserDetails, Long> {

}
