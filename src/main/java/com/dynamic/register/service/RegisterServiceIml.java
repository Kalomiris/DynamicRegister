package com.dynamic.register.service;

import com.dynamic.register.DynamicRegisterApplication;
import com.dynamic.register.data.UserData;
import com.dynamic.register.entity.DynamicRegisterUser;
import com.dynamic.register.model.UserModel;
import com.dynamic.register.repository.RegisterUserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class RegisterServiceIml implements RegisterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterServiceIml.class);

    @Autowired
    private RegisterUserRepo registerUserRepo;

    @Override
    public boolean saveUser(UserModel userModel) {
        LOGGER.info("RegisterServiceIml/saveUser");
        DynamicRegisterUser registerUser = new DynamicRegisterUser();
        registerUser.setUsername(userModel.getUserName());
        registerUser.setPassword(userModel.getPassword());
        registerUser.setEmail(userModel.getEmail());
        registerUserRepo.save(registerUser);
        return false;
    }

    @Override
    public List<UserData> findAll() {
        LOGGER.info("RegisterServiceIml/findAll");
        return registerUserRepo.findAll().
                stream().
                map(userEntity -> new UserData(
                        userEntity.getUsername(),
                        userEntity.getPassword(),
                        userEntity.getEmail())).
                collect(Collectors.toList());

    }

    @Override
    public UserData findDynamicRegisterUserByPassword(String password) {
        LOGGER.info("RegisterServiceIml/findDynamicRegisterUserByPassword");
        DynamicRegisterUser userEntity = new DynamicRegisterUser();
        userEntity = registerUserRepo.findDynamicRegisterUserByPassword(password);
        return new UserData(userEntity.getUsername(), userEntity.getPassword(), userEntity.getEmail());
    }


}
