package com.restaurant.cdstechchallenge.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SubmitRestaurantRequest {
    public SubmitRestaurantRequest() {
    }

    public SubmitRestaurantRequest(String name) {
        this.name = name;
    }

    @NotNull(message = "Provide some value cannot be null")
    @NotEmpty(message = "Provide some value cannot be empty")
    @Size(max = 150, message = "Characters should not exceed more than 150")
    private String name;

    public String getName() {return name.trim();}

    public void setName(String name) {
        this.name = name.trim();
    }
}
