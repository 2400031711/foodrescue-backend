package com.klu.food_rescue_backend.controller;

import com.klu.food_rescue_backend.model.User;
import com.klu.food_rescue_backend.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // Signup
    @PostMapping("/signup")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }

    // Login
    @PostMapping("/login")
    public User login(@RequestBody User user) {
        return userService.login(user.getEmail(), user.getPassword());
    }
}