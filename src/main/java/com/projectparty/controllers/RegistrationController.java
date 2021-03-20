package com.projectparty.controllers;

import com.projectparty.entities.User;
import com.projectparty.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @PostMapping("api/auth/signup")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/api/auth/signup")
    public String signup(Model model) {
        model.addAttribute("userForm", new User());

        return "signup";
    }
}
