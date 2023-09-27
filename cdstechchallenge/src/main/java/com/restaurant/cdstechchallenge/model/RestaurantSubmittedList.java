package com.restaurant.cdstechchallenge.model;

import jakarta.persistence.*;

@Entity
@Table(name = "restaurant_submitted_list")
public class RestaurantSubmittedList {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "name",unique = true, nullable = false, length = 150)
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
