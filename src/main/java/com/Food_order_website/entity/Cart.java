package com.Food_order_website.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Food food;

    private String foodName;

    @ManyToOne
    private User user;

    private int quantity;

    private long totalPrice;

    @ManyToOne
    private Restaurant restaurantId;

    private String foodImageUrl;

    private String restaurantName;
}
