package com.klu.food_rescue_backend.service;

import com.klu.food_rescue_backend.model.FoodRequest;
import java.util.List;

public interface RequestService {

    FoodRequest createRequest(Long userId, Long foodId);

    List<FoodRequest> getAllRequests();

    FoodRequest updateStatus(Long requestId, String status);
}