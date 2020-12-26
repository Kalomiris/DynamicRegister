package com.dynamic.register.service;

import com.dynamic.register.common.utils.PasswordGenerator;
import com.dynamic.register.entity.UserDetails;
import com.dynamic.register.mail.sender.EmailService;
import com.dynamic.register.model.email.Emailmodel;
import com.dynamic.register.model.user.CredentialModel;
import com.dynamic.register.model.user.UserDetailsModel;
import com.dynamic.register.repository.UserDetailsRepo;
import com.dynamic.register.wrapper.response.ResponseData;
import com.dynamic.register.wrapper.response.ResponseWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserDetailServiceIml implements UserDetailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailServiceIml.class);

    private final UserDetailsRepo userDetailsRepo;

    private final CredentialService credentialService;
    
    private final EmailService emailService;

    public UserDetailServiceIml(UserDetailsRepo userDetailsRepo, CredentialService credentialService, EmailService emailService) {
        this.userDetailsRepo = userDetailsRepo;
        this.credentialService = credentialService;
        this.emailService = emailService;
    }

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
            registerUser.setPic(userDetailsModel.getPicByte());
            userDetailsRepo.save(registerUser);
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

    @Override
    public List<UserDetailsModel> getUserList() {
        return userDetailsRepo.findAll().stream().map(userEntity ->{

            UserDetailsModel userDetailsModel = new UserDetailsModel();
            userDetailsModel.setId(userEntity.getId());
            userDetailsModel.setAddress(userEntity.getAddress());
            userDetailsModel.setEmail(userEntity.getEmail());
            userDetailsModel.setFirstName(userEntity.getFirstName());
            userDetailsModel.setLastName(userEntity.getLastName());
            userDetailsModel.setPhone(userEntity.getPhone());

            return userDetailsModel;
        }).collect(Collectors.toList());
    }

    @Override
    public String deleteUser(long id) {
        userDetailsRepo.deleteById(id);
        return null;
    }

    @Override
    public UserDetailsModel extractFileData(String fileData) {
        String[] data = fileData.split("\n");
        UserDetailsModel userDetailsModel = new UserDetailsModel();
        for (String element : data){
            if (element.contains("A")){
                userDetailsModel.setLastName(element);
            }
        }
        return null;
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
}
