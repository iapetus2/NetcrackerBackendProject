package com.projectparty.controllers;

import com.projectparty.entities.User;
import com.projectparty.entities.UserRoleEnum;
import com.projectparty.requests.LoginRequest;
import com.projectparty.requests.SignupRequest;
import com.projectparty.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/api/auth/signup")
    public ResponseEntity<?> addUser(@RequestBody SignupRequest signupRequest) {
        User user = new User(signupRequest.getUsername(),
                signupRequest.getEmail(),
                encoder.encode(signupRequest.getPassword()));
        user.setRole(UserRoleEnum.ROLE_USER);
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
