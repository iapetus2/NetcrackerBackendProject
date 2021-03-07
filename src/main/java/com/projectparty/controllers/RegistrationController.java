package com.projectparty.controllers;

import com.projectparty.entities.User;
import com.projectparty.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/auth/register")
    public String register(Model model) {
        model.addAttribute("userForm", new User());

        return "register";
    }

    @PostMapping("api/auth/register")
    public String addUser(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "register";
        }

        if (!userService.save(userForm)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "register";
        }

        return "redirect:/";
    }
}
