package com.klu.food_rescue_backend.service;

import com.klu.food_rescue_backend.model.*;
import com.klu.food_rescue_backend.repository.FoodRepository;
import com.klu.food_rescue_backend.repository.RequestRepository;
import com.klu.food_rescue_backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final UserRepository userRepository;
    private final FoodRepository foodRepository;

    public RequestServiceImpl(RequestRepository requestRepository,
                              UserRepository userRepository,
                              FoodRepository foodRepository) {
        this.requestRepository = requestRepository;
        this.userRepository = userRepository;
        this.foodRepository = foodRepository;
    }

    // ✅ CREATE REQUEST
    @Override
    public FoodRequest createRequest(Long userId, Long foodId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        FoodDonation food = foodRepository.findById(foodId)
                .orElseThrow(() -> new RuntimeException("Food not found"));

        // prevent duplicate request
        Optional<FoodRequest> existing =
        		requestRepository.findByUserIdAndFoodDonationId(userId, foodId);

        if (existing.isPresent()) {
            throw new RuntimeException("You already requested this food");
        }

        // check availability
        if (food.getStatus() != FoodStatus.AVAILABLE) {
            throw new RuntimeException("Food not available");
        }

        FoodRequest request = new FoodRequest();
        request.setUser(user);
        request.setFoodDonation(food);
        request.setStatus(RequestStatus.PENDING);

        return requestRepository.save(request);
    }

    // ✅ GET ALL REQUESTS
    @Override
    @Transactional
    public List<FoodRequest> getAllRequests() {
        return requestRepository.findAll();
    }

    // ✅ UPDATE STATUS
    @Override
    public FoodRequest updateStatus(Long requestId, String status) {

        FoodRequest request = requestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        RequestStatus reqStatus;

        try {
            reqStatus = RequestStatus.valueOf(status.toUpperCase());
        } catch (Exception e) {
            throw new RuntimeException("Invalid status: " + status);
        }

        request.setStatus(reqStatus);

        FoodDonation food = request.getFoodDonation();

        if (reqStatus == RequestStatus.ACCEPTED) {
            food.setStatus(FoodStatus.REQUESTED);
            foodRepository.save(food);
        }

        if (reqStatus == RequestStatus.REJECTED) {
            food.setStatus(FoodStatus.AVAILABLE);
            foodRepository.save(food);
        }

        return requestRepository.save(request);
    }
}