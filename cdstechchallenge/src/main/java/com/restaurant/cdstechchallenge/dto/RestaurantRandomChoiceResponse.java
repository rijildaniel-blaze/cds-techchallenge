package com.restaurant.cdstechchallenge.dto;

public class RestaurantRandomChoiceResponse {
    private Integer status;

    private RestaurantData data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public RestaurantData getData() {
        return data;
    }

    public void setData(RestaurantData data) {
        this.data = data;
    }
}
