package com.dynamic.register.RegisterResource;

import com.dynamic.register.model.user.UserDetailsModel;
import com.dynamic.register.service.UserDetailService;
import com.dynamic.register.wrapper.response.ResponseWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/details")
public class UserDetailController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailController.class);

    private final UserDetailService userDetailService;

    public UserDetailController(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @PostMapping(path = "save/user", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResponseWrapper> saveUser(@Valid @RequestBody UserDetailsModel newUser) throws Exception {
        LOGGER.info("DynamicRegisterController/saveUser");
        return ResponseEntity.ok(userDetailService.saveUser(newUser));
    }


    @PostMapping(path = "verify/user", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserDetailsModel> verifyUser(@Valid @RequestBody UserDetailsModel verifiedUser) throws Exception {
        LOGGER.info("DynamicRegisterController/saveUser");
        return ResponseEntity.ok(verifiedUser);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        return errors;
    }


//    @PostMapping(path = "verify/user", consumes = "application/json", produces = "application/json")
//    @ResponseStatus(HttpStatus.CREATED)
//    public UserData findByPassword(@RequestBody BaseParameters parameters) {
//        LOGGER.info("DynamicRegisterController/findbypassword");
//        String password = parameters.getPassword();
//        return registerService.findDynamicRegisterUserByPassword(password);
//    }
//
//    @GetMapping(value = "/findAll", produces = "application/json")
//    public List<UserData> findAll() {
//        LOGGER.info("DynamicRegisterController/findAll");
//        return registerService.findAll();
//    }
}
