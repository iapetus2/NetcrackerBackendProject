package com.projectparty.controllers;

import com.projectparty.entities.User;
import com.projectparty.entities.RoleType;
import com.projectparty.requests.SignupRequest;
import com.projectparty.response.MessageResponse;
import com.projectparty.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    PasswordEncoder encoder;

    @CrossOrigin(origins = "*")
    @PostMapping("/api/auth/signup")
    public ResponseEntity<?> addUser(@RequestBody SignupRequest signupRequest) {
        User user = new User(signupRequest.getUsername(),
                signupRequest.getEmail(),
                encoder.encode(signupRequest.getPassword()));
        user.setRole(RoleType.ROLE_USER);
        userService.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
