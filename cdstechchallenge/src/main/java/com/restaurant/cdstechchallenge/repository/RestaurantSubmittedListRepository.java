package com.restaurant.cdstechchallenge.repository;

import com.restaurant.cdstechchallenge.model.RestaurantSubmittedList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantSubmittedListRepository extends JpaRepository<RestaurantSubmittedList,Integer> {
    boolean existsByNameIgnoreCaseAndIdNot(String name, Integer id);

    boolean existsByNameIgnoreCase(String name);
}
