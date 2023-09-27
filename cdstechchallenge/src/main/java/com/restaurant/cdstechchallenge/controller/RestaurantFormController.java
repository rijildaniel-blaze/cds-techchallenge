package com.restaurant.cdstechchallenge.controller;

import com.restaurant.cdstechchallenge.dto.*;
import com.restaurant.cdstechchallenge.model.RestaurantSubmittedList;
import com.restaurant.cdstechchallenge.service.RestaurantFormService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RestaurantFormController {

    @Autowired
    private RestaurantFormService restaurantFormService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/restaurant")
    public ResponseEntity<SubmitRestaurantResponse> submitRestaurant(@Valid @RequestBody SubmitRestaurantRequest submitRestaurantRequest) {
        RestaurantSubmittedList restaurantSubmittedList =  restaurantFormService.submitRestaurant(null, submitRestaurantRequest);
        SubmitRestaurantResponse submitRestaurantResponse = new SubmitRestaurantResponse();
        if(restaurantSubmittedList != null) {
            submitRestaurantResponse.setStatus(201);
            submitRestaurantResponse.setMessage("Successfully added the restaurant");
            submitRestaurantResponse.setData(modelMapper.map(restaurantSubmittedList, RestaurantData.class));
            return ResponseEntity.status(HttpStatus.CREATED).body(submitRestaurantResponse);
        } else {
            submitRestaurantResponse.setStatus(409);
            submitRestaurantResponse.setMessage(submitRestaurantRequest.getName() + " already added");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(submitRestaurantResponse);
        }

    }

    @PutMapping("/restaurant/{id}")
    public ResponseEntity<SubmitRestaurantResponse> updateRestaurant(@PathVariable Integer id, @Valid @RequestBody SubmitRestaurantRequest submitRestaurantRequest) {
        RestaurantSubmittedList restaurantSubmittedList =  restaurantFormService.submitRestaurant(id, submitRestaurantRequest);
        SubmitRestaurantResponse submitRestaurantResponse = new SubmitRestaurantResponse();
        if(restaurantSubmittedList != null) {
            submitRestaurantResponse.setStatus(200);
            submitRestaurantResponse.setMessage("Successfully updated the restaurant");
            submitRestaurantResponse.setData(modelMapper.map(restaurantSubmittedList, RestaurantData.class));
            return ResponseEntity.ok().body(submitRestaurantResponse);
        } else {
            submitRestaurantResponse.setStatus(400);
            submitRestaurantResponse.setMessage(submitRestaurantRequest.getName() + " already exist");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(submitRestaurantResponse);
        }
    }

    @DeleteMapping("/restaurant/{id}")
    public ResponseEntity<String> deleteRestaurant(@PathVariable Integer id) {
        restaurantFormService.deleteRestaurant(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/restaurant")
    public ResponseEntity<RestaurantListResponse> getRestaurantList()   {
        List<RestaurantData>  restaurantDataList = modelMapper.map(restaurantFormService.getRestaurantList(), List.class);
        RestaurantListResponse restaurantListResponse = new RestaurantListResponse();
        restaurantListResponse.setStatus(200);
        restaurantListResponse.setMessage("Fetched all the submitted restaurants");
        restaurantListResponse.setData(restaurantDataList);
        return ResponseEntity.ok().body(restaurantListResponse);
    }

    @GetMapping("/restaurant/random-choice")
    public ResponseEntity<RestaurantRandomChoiceResponse> getRestaurantRandomChoice()   {
        RestaurantSubmittedList restaurantSubmittedList =  restaurantFormService.getRestaurantRandomChoice();
        if(restaurantSubmittedList != null) {
            RestaurantData restaurantData = modelMapper.map(restaurantSubmittedList, RestaurantData.class);
            RestaurantRandomChoiceResponse restaurantRandomChoiceResponse =  new RestaurantRandomChoiceResponse();
            restaurantRandomChoiceResponse.setStatus(200);
            restaurantRandomChoiceResponse.setData(restaurantData);
            return ResponseEntity.ok().body(restaurantRandomChoiceResponse);
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }
}
