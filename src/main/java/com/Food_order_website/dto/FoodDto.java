package com.Food_order_website.dto;

import lombok.Data;

@Data
public class FoodDto {
    private long id;

    private String foodName;

    private String foodDescription;

    private String foodImage;

    private Long foodPrice;

    private boolean vegetarian;

    private boolean nonVegetarian;
}
