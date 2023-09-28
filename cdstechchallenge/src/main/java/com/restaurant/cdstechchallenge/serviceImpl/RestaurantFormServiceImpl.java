package com.restaurant.cdstechchallenge.serviceImpl;

import com.restaurant.cdstechchallenge.dto.RestaurantData;
import com.restaurant.cdstechchallenge.dto.SubmitRestaurantRequest;
import com.restaurant.cdstechchallenge.model.RestaurantSubmittedList;
import com.restaurant.cdstechchallenge.repository.RestaurantSubmittedListRepository;
import com.restaurant.cdstechchallenge.service.RestaurantFormService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;


@Service
public class RestaurantFormServiceImpl implements RestaurantFormService {

    @Autowired
    private RestaurantSubmittedListRepository restaurantSubmittedListRepository;

    private ModelMapper modelMapper;

    @Autowired
    private Random random;

    @Autowired
    public RestaurantFormServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        modelMapper.addMappings(new PropertyMap<SubmitRestaurantRequest, RestaurantSubmittedList>() {
            @Override
            protected void configure() {
                skip(destination.getId());
            }
        });
    }
    public RestaurantFormServiceImpl(RestaurantSubmittedListRepository restaurantSubmittedListRepository, ModelMapper modelMapper, Random random) {
        this.restaurantSubmittedListRepository = restaurantSubmittedListRepository;
        this.modelMapper = modelMapper;
        this.modelMapper.addMappings(new PropertyMap<SubmitRestaurantRequest, RestaurantSubmittedList>() {
            @Override
            protected void configure() {
                skip(destination.getId());
            }
        });
        this.random = random;
    }

    @Override
    public RestaurantSubmittedList submitRestaurant(Integer id, SubmitRestaurantRequest submitRestaurantRequest) {
        RestaurantSubmittedList restaurantSubmittedList = null;
        if(id != null)  {
            restaurantSubmittedList = restaurantSubmittedListRepository.findById(id).orElse(null);
            if(restaurantSubmittedList != null) {
                if(!restaurantSubmittedListRepository.existsByNameIgnoreCaseAndIdNot(submitRestaurantRequest.getName(), id))  {
                    restaurantSubmittedList.setName(submitRestaurantRequest.getName());
                } else {
                    restaurantSubmittedList = null;
                }
            }
        } else {
            if(!restaurantSubmittedListRepository.existsByNameIgnoreCase(submitRestaurantRequest.getName())) {
                restaurantSubmittedList = modelMapper.map(submitRestaurantRequest, RestaurantSubmittedList.class);
            }
        }
        if(restaurantSubmittedList != null) {
            return restaurantSubmittedListRepository.save(restaurantSubmittedList);
        }
        return null;
    }

    @Override
    public void deleteRestaurant(Integer id) {
        restaurantSubmittedListRepository.deleteById(id);
        System.out.println("Deleted the restaurant with ID - " + id);
    }

    @Override
    public List<RestaurantSubmittedList> getRestaurantList() {
        return restaurantSubmittedListRepository.findAll();
    }

    @Override
    public RestaurantSubmittedList getRestaurantRandomChoice() {
        int id = random.nextInt((int) restaurantSubmittedListRepository.count())    ;
        return restaurantSubmittedListRepository.findAll().get(id);
    }
}
