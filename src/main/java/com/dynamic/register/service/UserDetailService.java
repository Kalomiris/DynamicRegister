package com.dynamic.register.service;

import com.dynamic.register.model.user.UserDetailsModel;
import com.dynamic.register.wrapper.response.ResponseWrapper;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserDetailService {

    ResponseWrapper saveUser(UserDetailsModel userDetailsModel) throws Exception;

    List<UserDetailsModel> getUserList();

    String deleteUser(long id);

    UserDetailsModel extractFileData(String fileData);
}
