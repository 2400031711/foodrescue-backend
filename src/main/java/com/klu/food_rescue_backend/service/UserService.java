package com.klu.food_rescue_backend.service;

import com.klu.food_rescue_backend.model.User;
import com.klu.food_rescue_backend.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Signup
    public User register(User user) {
        return userRepository.save(user);
    }

    // Login
    public User login(String email, String password) {
        User user = userRepository.findByEmail(email);

        if(user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
