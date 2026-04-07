package com.klu.food_rescue_backend.service;

import com.klu.food_rescue_backend.model.FoodDonation;
import com.klu.food_rescue_backend.model.FoodStatus;
import com.klu.food_rescue_backend.repository.FoodRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;

    public FoodServiceImpl(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Override
    public FoodDonation addFood(FoodDonation food) {
        food.setStatus(FoodStatus.AVAILABLE);
        return foodRepository.save(food);
    }

    @Override
    public List<FoodDonation> getAllFood() {
        return foodRepository.findAll();
    }

    @Override
    public FoodDonation getFoodById(Long id) {
        return foodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Food not found"));
    }

    @Override
    public FoodDonation updateStatus(Long id, String status) {
        FoodDonation food = getFoodById(id);
        food.setStatus(FoodStatus.valueOf(status.toUpperCase()));
        return foodRepository.save(food);
    }

    @Override
    public void deleteFood(Long id) {
        foodRepository.deleteById(id);
    }
}