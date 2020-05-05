package com.dynamic.register.RegisterResource;


import com.dynamic.register.model.user.CredentialModel;
import com.dynamic.register.service.CredentialService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/signup")
public class SingUpController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SingUpController.class);

    private final CredentialService credentialService;

    public SingUpController(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @PostMapping(path = "checkCredentials", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CredentialModel> checkCredentials(@RequestBody CredentialModel userCredential) throws Exception {
        LOGGER.info("DynamicRegisterController/saveUser");
        return ResponseEntity.ok(credentialService.checkCredential(userCredential));
    }
}
