package com.dynamic.register.controller;

import com.dynamic.register.gcp.OcrVisionService;
import com.dynamic.register.model.user.UserDetailsModel;
import com.dynamic.register.repository.FileRepo;
import com.dynamic.register.service.UserDetailService;
import com.dynamic.register.wrapper.response.ResponseWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/details")
public class UserDetailController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailController.class);

    private final UserDetailService userDetailService;
    final FileRepo imageRepository;
    final OcrVisionService ocrVisionService;

    public UserDetailController(UserDetailService userDetailService, FileRepo imageRepository, OcrVisionService ocrVisionService) {
        this.userDetailService = userDetailService;
        this.imageRepository = imageRepository;
        this.ocrVisionService = ocrVisionService;
    }

    @PostMapping("save/user")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> saveUser(@RequestParam("file") MultipartFile file) throws Exception {
        LOGGER.info("DynamicRegisterController/saveUser");
        String message;
        ocrVisionService.ocrLabeling(file);
        String result = ocrVisionService.extractText(file);
        LOGGER.info(result + "###########################");
        userDetailService.extractFileData(result);
        message = "Successfully uploaded!";
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }


    @PostMapping(path = "verify/user", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserDetailsModel> verifyUser(@Valid @RequestBody UserDetailsModel verifiedUser) throws Exception {
        LOGGER.info("DynamicRegisterController/saveUser");
        return ResponseEntity.ok(verifiedUser);
    }

    @PostMapping(path = "get/users", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<List<UserDetailsModel>> getUserList() throws Exception {
        LOGGER.info("DynamicRegisterController/getUserList");
        return ResponseEntity.ok(userDetailService.getUserList());
    }

    @DeleteMapping(path = "delete/users/{id}", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> deleteUser(@PathVariable("id") int id) throws Exception {
        LOGGER.info("DynamicRegisterController/getUserList");
        return ResponseEntity.ok(userDetailService.deleteUser(id));
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        return errors;
    }
}
