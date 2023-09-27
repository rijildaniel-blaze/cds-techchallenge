package com.restaurant.cdstechchallenge.service;

import com.restaurant.cdstechchallenge.dto.RestaurantData;
import com.restaurant.cdstechchallenge.dto.SubmitRestaurantRequest;
import com.restaurant.cdstechchallenge.model.RestaurantSubmittedList;

import java.util.List;

public interface RestaurantFormService {
    RestaurantSubmittedList submitRestaurant(Integer id, SubmitRestaurantRequest submitRestaurantRequest);

    void deleteRestaurant(Integer id);

    List<RestaurantSubmittedList> getRestaurantList();

    RestaurantSubmittedList getRestaurantRandomChoice();
}
