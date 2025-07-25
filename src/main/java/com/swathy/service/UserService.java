package com.swathy.service;


import com.swathy.entity.User;
import com.swathy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    //Register new user
    public User registerUser(User user){
        return userRepository.save(user);
    }

    //find by email (for logging and checking)
    public Optional<User> getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    //get user by id(for viewing profile)
    public Optional<User> getUserById(int id){
        return userRepository.findById(id);
    }

    public User login(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
    }


}
