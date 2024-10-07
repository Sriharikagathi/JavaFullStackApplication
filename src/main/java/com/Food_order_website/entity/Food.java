package com.Food_order_website.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String foodName;

    private String foodDescription;

    private String foodImage;

    private Long foodPrice;

    private double foodRating;

    private boolean bestseller;

    private boolean vegetarian;

    private boolean nonVegetarian;


    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private List<Review> reviews;

}
