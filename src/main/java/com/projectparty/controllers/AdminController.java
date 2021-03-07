package com.projectparty.controllers;

import com.projectparty.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("api/admin")
    public String userList(Model model) {
        model.addAttribute("allUsers", userService.readAll());
        return "admin";
    }

    @PostMapping("api/admin")
    public String deleteUser(@RequestParam(required = true, defaultValue = "" ) int userId,
                              @RequestParam(required = true, defaultValue = "" ) String action,
                              Model model) {
        if (action.equals("delete")){
            userService.delete(userId);
        }
        return "redirect:/api/admin";
    }

}
