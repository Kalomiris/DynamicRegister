package com.dynamic.register.service;

import com.dynamic.register.common.utils.PasswordGenerator;
import com.dynamic.register.entity.UserDetails;
import com.dynamic.register.mail.sender.EmailService;
import com.dynamic.register.model.email.Emailmodel;
import com.dynamic.register.model.user.CredentialModel;
import com.dynamic.register.model.user.UserDetailsModel;
import com.dynamic.register.repository.SaveUserDetailRepo;
import com.dynamic.register.wrapper.response.ResponseData;
import com.dynamic.register.wrapper.response.ResponseWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.Date;


@Service
public class UserDetailServiceIml implements UserDetailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailServiceIml.class);

    @Autowired
    private SaveUserDetailRepo saveUserDetailRepo;

    @Autowired
    private CredentialService credentialService;
    
    @Autowired
    private EmailService emailService;

    @Override
    public ResponseWrapper saveUser(UserDetailsModel userDetailsModel) throws Exception {
        ResponseData responseData = new ResponseData();
        try {
            LOGGER.info("RegisterServiceIml/saveUser");
            UserDetails registerUser = new UserDetails();
            registerUser.setFirstName(userDetailsModel.getFirstName());
            registerUser.setLastName(userDetailsModel.getLastName());
            registerUser.setEmail(userDetailsModel.getEmail());
            registerUser.setAddress(userDetailsModel.getAddress());
            registerUser.setPhone(userDetailsModel.getPhone());
            registerUser.setDate(new Date());
            saveUserDetailRepo.save(registerUser);
            CredentialModel credential = new CredentialModel();
            credential.setUserName(userDetailsModel.getEmail());
            credential.setPassword(PasswordGenerator.generatePassword());
            credentialService.saveCredentials(credential);
            sendEmail(registerUser, credential);
        }catch (Exception e){
            responseData.setStatus(HttpStatus.BAD_REQUEST);
            return new ResponseWrapper(responseData, null);
        }
        responseData.setStatus(HttpStatus.ACCEPTED);
        return new ResponseWrapper(responseData, null);
    }

    private void sendEmail(UserDetails registerUser, CredentialModel credential) {
        Emailmodel emailmodel = new Emailmodel();
        emailmodel.setEmail(registerUser.getEmail());
        emailmodel.setSubject("Generated Password");
        emailmodel.setText("Your new Password is");
        emailmodel.setPassword(credential.getPassword());
        try {

            emailService.sendEmail(emailmodel);
        }catch (MessagingException e){
            LOGGER.info(e.getMessage());
        }
    }

//    @Override
//    public List<UserData> findAll() {
//        LOGGER.info("RegisterServiceIml/findAll");
//        return registerUserRepo.findAll().
//                stream().
//                map(userEntity -> new UserData(
//                        userEntity.getFirstName(),
//                        userEntity.getPassword(),
//                        userEntity.getEmail())).
//                collect(Collectors.toList());
//
//    }
//
//    @Override
//    public UserData findDynamicRegisterUserByPassword(String password) {
//        LOGGER.info("RegisterServiceIml/findDynamicRegisterUserByPassword");
//        DynamicUser userEntity = new DynamicUser();
//        userEntity = registerUserRepo.findDynamicRegisterUserByPassword(password);
//        return new UserData(userEntity.getFirstName(), userEntity.getPassword(), userEntity.getEmail());
//    }


}
