package com.dynamic.register.RegisterResource;

import com.dynamic.register.data.UserData;
import com.dynamic.register.model.UserModel;
import com.dynamic.register.service.RegisterService;
import com.dynamic.register.wrapper.BaseWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/register")
public class DynamicRegisterController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicRegisterController.class);

    private final RegisterService registerService;

    public DynamicRegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping(path = "save/user", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@RequestBody BaseWrapper<UserData, UserModel> request) {
        LOGGER.info("DynamicRegisterController/saveUser");
        UserModel userModel = request.getModelList().get(0);
        registerService.saveUser(userModel);
    }

    @PostMapping(value = "/findbypassword", produces = "application/json")
    public UserData findByPassword(@RequestBody BaseWrapper<UserData, UserModel> request) {
        LOGGER.info("DynamicRegisterController/findbypassword");
        String password = request.getBaseParameters().getPassword();
        return registerService.findDynamicRegisterUserByPassword(password);
    }

    @GetMapping(value = "/findAll", produces = "application/json")
    public BaseWrapper<UserData, UserModel> findAll() {
        LOGGER.info("DynamicRegisterController/findAll");
        return new BaseWrapper<>(registerService.findAll(), null);
    }
}
