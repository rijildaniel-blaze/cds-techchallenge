package com.restaurant.cdstechchallenge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.restaurant.cdstechchallenge.dto.SubmitRestaurantRequest;
import com.restaurant.cdstechchallenge.model.RestaurantSubmittedList;
import com.restaurant.cdstechchallenge.service.RestaurantFormService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(RestaurantFormController.class)
class RestaurantFormControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RestaurantFormService restaurantFormService;

    RestaurantSubmittedList restaurantSubmittedList1;
    RestaurantSubmittedList restaurantSubmittedList2;

    SubmitRestaurantRequest submitRestaurantRequest;
    List<RestaurantSubmittedList> restaurantSubmittedListArray = new ArrayList<>();

    @BeforeEach
    void setUp() {
        submitRestaurantRequest = new SubmitRestaurantRequest("Test Restaurant");
        restaurantSubmittedList1 = new RestaurantSubmittedList(1, "Test Restaurant");
        restaurantSubmittedList2 = new RestaurantSubmittedList(2, "Test Restaurant 2");
        restaurantSubmittedListArray.add(restaurantSubmittedList1);
        restaurantSubmittedListArray.add(restaurantSubmittedList2);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testSubmitRestaurant() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(submitRestaurantRequest);

        Mockito.when(restaurantFormService.submitRestaurant(Mockito.eq(null), Mockito.any(SubmitRestaurantRequest.class)))
                .thenReturn(restaurantSubmittedList1);
        this.mockMvc.perform(post("/api/restaurant")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andDo(print()).andExpect(status().isCreated());
    }

    @Test
    void updateRestaurant() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(submitRestaurantRequest);

        Mockito.when(restaurantFormService.submitRestaurant(Mockito.eq(1), Mockito.any(SubmitRestaurantRequest.class)))
                .thenReturn(restaurantSubmittedList1);
        this.mockMvc.perform(put("/api/restaurant/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void deleteRestaurant() {
    }

    @Test
    void testGetRestaurantList() throws Exception {
        Mockito.when(restaurantFormService.getRestaurantList())
                .thenReturn(restaurantSubmittedListArray);
        this.mockMvc.perform(get("/api/restaurant"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getRestaurantRandomChoice() throws Exception {
        Mockito.when(restaurantFormService.getRestaurantRandomChoice())
                .thenReturn(restaurantSubmittedList2);
        this.mockMvc.perform(get("/api/restaurant/random-choice"))
                .andDo(print()).andExpect(status().isOk());
    }
}