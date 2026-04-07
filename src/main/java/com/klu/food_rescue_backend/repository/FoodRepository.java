package com.klu.food_rescue_backend.repository;

import com.klu.food_rescue_backend.model.FoodDonation;
import com.klu.food_rescue_backend.model.FoodStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<FoodDonation, Long> {

    List<FoodDonation> findByStatus(FoodStatus status);  // ✅ FIXED
}