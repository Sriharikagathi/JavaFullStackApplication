package com.Food_order_website.implService;

import com.Food_order_website.entity.Food;
import com.Food_order_website.entity.Restaurant;
import com.Food_order_website.repository.RestaurantRepository;
import com.Food_order_website.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImplRestaurantService implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;
    @Override
    public Restaurant CreateRestaurant(Restaurant restaurant) {
        Restaurant save = restaurantRepository.save(restaurant);
        return save;
    }
    @Override
    public Restaurant UpdateRestaurant(Restaurant restaurant, long restaurantId) {
        Restaurant restaurant1= new Restaurant();
        Optional<Restaurant> restaurant2 = restaurantRepository.findById(restaurantId);
        if(restaurant2.getClass()==null) {
            restaurant1.setId(restaurantId);
            restaurant1.setRestaurantName(restaurant.getRestaurantName());
            restaurant1.setRestaurantPhone(restaurant.getRestaurantPhone());
            restaurant1.setRestaurantEmail(restaurant.getRestaurantEmail());
            restaurant1.setRestaurantClosingTime(restaurant.getRestaurantClosingTime());
            restaurant1.setRestaurantStartTime(restaurant.getRestaurantStartTime());
            restaurant1.setRestaurantRating(restaurant.getRestaurantRating());
            restaurant1.setCuisineType(restaurant.getCuisineType());
            restaurant1.setFoodList(restaurant.getFoodList());
            restaurant1.setLocation(restaurant.getLocation());
        }
        Restaurant save = restaurantRepository.save(restaurant1);
        return save;


    }

    @Override
    public Restaurant DeleteRestaurant(long restaurantId) {
        return null;
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return List.of();
    }

    @Override
    public Restaurant getRestaurantById(long restaurantId) {
        return null;
    }

    @Override
    public List<Food> getRestaurantFoods(long restaurantId) {
        return List.of();
    }

    @Override
    public Restaurant addToFavorites(long restaurantId) {
        return null;
    }
}
