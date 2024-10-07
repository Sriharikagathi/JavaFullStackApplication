package com.Food_order_website.service;

import com.Food_order_website.entity.Food;
import com.Food_order_website.entity.Restaurant;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RestaurantService {
    public Restaurant CreateRestaurant(Restaurant restaurant);
    public Restaurant UpdateRestaurant(Restaurant restaurant,long restaurantId);
    public Restaurant DeleteRestaurant(long restaurantId);
    public List<Restaurant> getAllRestaurants();
    public Restaurant getRestaurantById(long restaurantId);
    public List<Food> getRestaurantFoods(long restaurantId);
    public Restaurant addToFavorites(long restaurantId);
}
