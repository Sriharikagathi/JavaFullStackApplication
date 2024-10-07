package com.Food_order_website.controller;

import com.Food_order_website.entity.Restaurant;
import com.Food_order_website.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/restuarant")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @PostMapping()
    public ResponseEntity<Restaurant> CreateRestaurant(@RequestBody Restaurant restaurant) {
        Restaurant restaurant1 = restaurantService.CreateRestaurant(restaurant);
        return ResponseEntity.ok(restaurant1);
    }

    @PutMapping("/updateRestaurant")
    public ResponseEntity<Restaurant> updateRestaurant(@RequestBody Restaurant restaurant, @PathVariable long restaurantId) {
        Restaurant restaurant1 = restaurantService.UpdateRestaurant(restaurant, restaurantId);
        return ResponseEntity.ok(restaurant1);
    }

}


