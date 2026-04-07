package com.klu.food_rescue_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "food_request")
public class FoodRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private FoodDonation foodDonation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    public FoodRequest() {}

    public Long getId() { return id; }

    public FoodDonation getFoodDonation() { return foodDonation; }
    public void setFoodDonation(FoodDonation foodDonation) { this.foodDonation = foodDonation; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public RequestStatus getStatus() { return status; }
    public void setStatus(RequestStatus status) { this.status = status; }
}