package com.dynamic.register.service;

import com.dynamic.register.model.user.UserDetailsModel;
import com.dynamic.register.wrapper.response.ResponseWrapper;

import javax.validation.Valid;

public interface UserDetailService {

    ResponseWrapper saveUser(UserDetailsModel userDetailsModel) throws Exception;

}
