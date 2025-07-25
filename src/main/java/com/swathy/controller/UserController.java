package com.swathy.controller;

import com.swathy.entity.User;
import com.swathy.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@Valid @RequestBody User user){
        return userService.registerUser(user);
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable int id){
        return userService.getUserById(id);
    }

    @GetMapping("/{email}")
    public Optional<User> getUserByEmail(@PathVariable String email){
        return userService.getUserByEmail(email);
    }

    @PostMapping("/login")
    public User login(@RequestBody User loginData) {
        return userService.login(loginData.getEmail(), loginData.getPassword());
    }

}
