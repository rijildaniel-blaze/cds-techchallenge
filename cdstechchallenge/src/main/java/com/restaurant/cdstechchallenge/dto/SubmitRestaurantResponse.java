package com.restaurant.cdstechchallenge.dto;


public class SubmitRestaurantResponse {

    private Integer status;

    private String message;

    private RestaurantData data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RestaurantData getData() {
        return data;
    }

    public void setData(RestaurantData data) {
        this.data = data;
    }

}
