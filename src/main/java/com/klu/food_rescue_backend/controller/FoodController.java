package com.klu.food_rescue_backend.controller;

import com.klu.food_rescue_backend.model.FoodDonation;
import com.klu.food_rescue_backend.model.FoodStatus;
import com.klu.food_rescue_backend.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
@CrossOrigin(origins = "*")
public class FoodController {

    @Autowired
    private FoodRepository foodRepo;

    // ✅ ADD FOOD
    @PostMapping
    public FoodDonation addFood(@RequestBody FoodDonation food) {
        food.setStatus(FoodStatus.AVAILABLE);   // auto set
        return foodRepo.save(food);
    }

    // ✅ GET ALL FOOD
    @GetMapping
    public List<FoodDonation> getAllFood() {
        return foodRepo.findAll();
    }

    // ✅ GET AVAILABLE FOOD
    @GetMapping("/available")
    public List<FoodDonation> getAvailableFood() {
        return foodRepo.findByStatus(FoodStatus.AVAILABLE);
    }

    // ✅ UPDATE STATUS
    @PutMapping("/{id}")
    public FoodDonation updateFoodStatus(
            @PathVariable Long id,
            @RequestParam String status
    ) {
        FoodDonation food = foodRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Food not found"));

        food.setStatus(FoodStatus.valueOf(status.toUpperCase())); // safe conversion
        return foodRepo.save(food);
    }
}