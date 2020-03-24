package com.dynamic.register.service;

import com.dynamic.register.model.UserModel;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService{


    public boolean saveUser (UserModel userModel){


        return false;
    }

    @Override
    public boolean checkForRegisterUser(String password) {


        return false;
    }
}
