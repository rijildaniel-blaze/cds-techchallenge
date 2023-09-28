package com.restaurant.cdstechchallenge.serviceImpl;

import com.restaurant.cdstechchallenge.dto.SubmitRestaurantRequest;
import com.restaurant.cdstechchallenge.model.RestaurantSubmittedList;
import com.restaurant.cdstechchallenge.repository.RestaurantSubmittedListRepository;
import com.restaurant.cdstechchallenge.service.RestaurantFormService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;

class RestaurantFormServiceImplTest {

    @Mock
    private RestaurantSubmittedListRepository restaurantSubmittedListRepository;

    private RestaurantFormService restaurantFormService;
    AutoCloseable autoCloseable;

    private RestaurantSubmittedList restaurantSubmittedList;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        restaurantFormService = new RestaurantFormServiceImpl(restaurantSubmittedListRepository, new ModelMapper(), new Random());
        restaurantSubmittedList = new RestaurantSubmittedList(null, "Test Name");
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void submitRestaurant() {
        Mockito.mock(RestaurantSubmittedListRepository.class);
        Mockito.mock(RestaurantSubmittedList.class);
        Mockito.when(restaurantSubmittedListRepository.save(Mockito.any(RestaurantSubmittedList.class)))
                .thenReturn(restaurantSubmittedList);

        assertThat(restaurantFormService
                .submitRestaurant(null, new SubmitRestaurantRequest("Test Name")))
                .usingRecursiveComparison().ignoringFields("id").isEqualTo(restaurantSubmittedList);
    }


    @Test
    void testGetRestaurantList() {
        Mockito.mock(RestaurantSubmittedListRepository.class);
        Mockito.mock(RestaurantSubmittedList.class);
        Mockito.when(restaurantSubmittedListRepository.findAll())
                .thenReturn(new ArrayList<RestaurantSubmittedList>(Collections.singletonList(restaurantSubmittedList)));
        assertThat(restaurantFormService.getRestaurantList().get(0).getName()).isEqualTo(restaurantSubmittedList.getName());
    }

    @Test
    void testGetRestaurantRandomChoice() {
        Mockito.mock(RestaurantSubmittedListRepository.class);
        Mockito.mock(RestaurantSubmittedList.class);
        Mockito.when(restaurantSubmittedListRepository.count())
                .thenReturn(1l);
        Mockito.when(restaurantSubmittedListRepository.findAll())
                .thenReturn(new ArrayList<RestaurantSubmittedList>(Collections.singletonList(restaurantSubmittedList)));
        assertThat(restaurantFormService.getRestaurantRandomChoice()).isInstanceOf(RestaurantSubmittedList.class);
    }
}