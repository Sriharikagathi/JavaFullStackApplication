package com.Food_order_website.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String RestaurantName;

    private String RestaurantPhone;

    private String RestaurantEmail;

    private int RestaurantRating;

    private LocalDateTime RestaurantStartTime;

    private LocalDateTime RestaurantClosingTime;

    private String cuisineType;

    @OneToMany
    @JoinColumn(name = "food_id")
    private List<Food> FoodList = new ArrayList<Food>();

    @OneToOne
    @JoinColumn(name = "location_id")
    private Location  location;

    @ManyToOne
    private Review review;





}
