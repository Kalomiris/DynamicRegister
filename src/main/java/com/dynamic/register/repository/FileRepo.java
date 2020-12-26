package com.dynamic.register.repository;

import com.dynamic.register.entity.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileRepo extends JpaRepository<ImageModel, Long> {

    Optional<ImageModel> findByName(String name);
}
