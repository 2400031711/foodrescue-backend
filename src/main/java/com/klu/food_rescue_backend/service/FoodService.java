package com.klu.food_rescue_backend.service;

import com.klu.food_rescue_backend.model.FoodDonation;
import java.util.List;

public interface FoodService {

    FoodDonation addFood(FoodDonation food);

    List<FoodDonation> getAllFood();

    FoodDonation getFoodById(Long id);

    FoodDonation updateStatus(Long id, String status);

    void deleteFood(Long id);
}