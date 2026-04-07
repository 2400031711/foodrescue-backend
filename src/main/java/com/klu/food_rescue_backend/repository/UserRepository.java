package com.klu.food_rescue_backend.repository;

import com.klu.food_rescue_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}