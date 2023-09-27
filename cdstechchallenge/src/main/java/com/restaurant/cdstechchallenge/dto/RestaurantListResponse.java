package com.restaurant.cdstechchallenge.dto;

import java.util.List;

public class RestaurantListResponse {
    private Integer status;

    private String message;

    private List<RestaurantData> data;

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

    public List<RestaurantData> getData() {
        return data;
    }

    public void setData(List<RestaurantData> data) {
        this.data = data;
    }
}
