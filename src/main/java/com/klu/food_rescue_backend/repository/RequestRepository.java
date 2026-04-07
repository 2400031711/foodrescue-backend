package com.klu.food_rescue_backend.repository;

import com.klu.food_rescue_backend.model.FoodRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RequestRepository extends JpaRepository<FoodRequest, Long> {

    Optional<FoodRequest> findByUserIdAndFoodDonationId(Long userId, Long foodId);
}