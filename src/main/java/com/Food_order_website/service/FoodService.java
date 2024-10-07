package com.Food_order_website.service;

import com.Food_order_website.dto.CartDto;
import com.Food_order_website.dto.FoodDto;
import com.Food_order_website.entity.Cart;
import com.Food_order_website.entity.Food;
import com.Food_order_website.entity.Review;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface FoodService {

    public Food addFood(Food food);

    public FoodDto updateFood(long foodId, FoodDto foodDto,boolean vegetarian,boolean nonVegetarian);

    public void deleteFood(long foodId);

    public Food getFoodById(long id);

    public List<Food> findAllFoods();

    public List<Food> isVegetarian(boolean vegetarian);

    public List<Food> isNonVegetarian(boolean nonVegetarian)throws Exception;

    public List<Food> isBestSeller(boolean bestSeller);

    public List<Review> FoodReview(long foodId, long UserId,Review review);


}
