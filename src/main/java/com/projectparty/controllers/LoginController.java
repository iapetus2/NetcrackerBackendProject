package com.projectparty.controllers;

import com.projectparty.dao.RoleDao;
import com.projectparty.dao.UserDao;
import com.projectparty.entities.User;
import com.projectparty.requests.LoginRequest;
import com.projectparty.response.JwtResponse;
import com.projectparty.security.jwt.JwtUtils;
import com.projectparty.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class LoginController {
    private static final Logger logger = Logger.getLogger(LoginController.class.getName());

    AuthenticationManager authenticationManager;
    UserDao userDao;
    RoleDao roleDao;
    PasswordEncoder encoder;
    JwtUtils jwtUtils;

    @Autowired
    public LoginController(AuthenticationManager authenticationManager, UserDao userDao, RoleDao roleDao, PasswordEncoder encoder, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        logger.log(Level.INFO, loginRequest.toString());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        User userDetails = (User) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(
                jwt,
                userDetails.getUserId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                userDetails.getRole(),
                userDetails.getCash()));
    }
}



