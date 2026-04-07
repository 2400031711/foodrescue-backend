package com.klu.food_rescue_backend.controller;

import com.klu.food_rescue_backend.model.FoodRequest;
import com.klu.food_rescue_backend.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/request")

@CrossOrigin("*")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @GetMapping("/test")
    public String test() {
        return "REQUEST CONTROLLER WORKING";
    }

    @PostMapping("/create")
    public FoodRequest createRequest(@RequestParam Long userId,
                                      @RequestParam Long foodId) {
        return requestService.createRequest(userId, foodId);
    }

    @GetMapping("/all")
    public List<FoodRequest> getAllRequests() {
        return requestService.getAllRequests();
    }

    @PutMapping("/status/{id}")
    public FoodRequest updateStatus(@PathVariable Long id,
                                     @RequestParam String status) {
        return requestService.updateStatus(id, status);
    }
}