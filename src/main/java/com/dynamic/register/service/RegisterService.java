package com.dynamic.register.service;

import com.dynamic.register.data.UserData;
import com.dynamic.register.model.UserModel;

import java.util.List;

public interface RegisterService {

    boolean saveUser(UserModel userModel);

    List<UserData> findAll();

    UserData findDynamicRegisterUserByPassword(String password);
}
