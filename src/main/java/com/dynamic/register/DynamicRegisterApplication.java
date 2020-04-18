package com.dynamic.register;

import com.dynamic.register.model.user.UserDetailsModel;
import com.dynamic.register.wrapper.BaseParameters;
import com.dynamic.register.wrapper.request.RequestWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DynamicRegisterApplication{// implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DynamicRegisterApplication.class);

//    @Autowired
//    private RegisterService registerService;

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        log.info(mapper.writeValueAsString(new RequestWrapper(new UserDetailsModel(), new BaseParameters())));
        SpringApplication.run(DynamicRegisterApplication.class, args);
    }




























//    @Override
//    public void run(String ... args){
//        log.info("StartApplication...");
//
////        System.out.println("\nsave Data");
////        User newUser = new User();
////        newUser.setUserName("kalom");
////        newUser.setPassword("1233kalom");
////        newUser.setEmail("kalom@gmail.com");
////        registerService.saveUser(newUser);
//
//        System.out.println("\nfindAll()");
//        registerService.findAll().forEach(x -> log.info("\n\n" + x.getUserName() +" , "+ x.getPassword() +" , "+ x.getEmail()));
//
//        System.out.println("\nfindByPassword()");
//        UserData userData = registerService.findDynamicRegisterUserByPassword("1233kalom");
//        log.info("\n\n" + userData.getUserName() +" , "+ userData.getPassword() +" , "+ userData.getEmail());
//    }
}
