package com.Food_order_website.implService;

import com.Food_order_website.dto.FoodDto;
import com.Food_order_website.entity.*;
import com.Food_order_website.repository.*;
import com.Food_order_website.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ImplFoodService implements FoodService {

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    Map<String, String> map = new HashMap<>();


    @Override
    public Food addFood(Food food) {
        return foodRepository.save(food);
    }

    @Override
    public FoodDto updateFood(long foodId, FoodDto foodDto, boolean vegetarian, boolean nonVegetarian) {
        Food food = foodRepository.findById(foodId).orElseThrow(() -> new RuntimeException("Food not found"));

        // Update the fields of the food entity with values from foodDto
        food.setFoodName(foodDto.getFoodName());
        food.setFoodPrice(foodDto.getFoodPrice());
        food.setFoodImage(foodDto.getFoodImage());
        food.setFoodDescription(foodDto.getFoodDescription());
        food.setVegetarian(foodDto.isVegetarian());
        food.setNonVegetarian(foodDto.isNonVegetarian());

        // Save the updated food entity back to the repository
        foodRepository.save(food);

        // Create a new FoodDto to return the updated information
        FoodDto updatedFoodDto = new FoodDto();
        updatedFoodDto.setId(food.getId());
        updatedFoodDto.setFoodName(food.getFoodName());
        updatedFoodDto.setFoodPrice(food.getFoodPrice());
        updatedFoodDto.setFoodImage(food.getFoodImage());
        updatedFoodDto.setFoodDescription(food.getFoodDescription());
        updatedFoodDto.setVegetarian(food.isVegetarian());
        updatedFoodDto.setNonVegetarian(food.isNonVegetarian());

        return updatedFoodDto;
    }

    @Override
    public void deleteFood(long foodId) {
        foodRepository.deleteById(foodId);
    }

    @Override
    public Food getFoodById(long id) {
        Food foodNotFound = foodRepository.findById(id).orElseThrow(() -> new RuntimeException("Food Not Found"));
        return foodNotFound;
    }

    @Override
    public List<Food> findAllFoods() {
        // Fetch all foods from the repository
        return foodRepository.findAll();
    }

    @Override
    public List<Food> isVegetarian(boolean vegetarian) {
        List<Food> allFoods = foodRepository.findAll();
        List<Food> collect = allFoods.stream().filter(food -> food.isVegetarian() == true).collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<Food> isNonVegetarian(boolean nonVegetarian) {
        List<Food> allFood = foodRepository.findAll();
        List<Food> collect = allFood.stream().filter(food -> food.isNonVegetarian() == true).collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<Food> isBestSeller(boolean bestSeller) {
        List<Food> allFood = foodRepository.findAll();
        List<Food> collect = allFood.stream().filter(food -> food.isBestseller() == true).collect(Collectors.toList());
        return collect;
    }



    @Override
    public List<Review> FoodReview(long foodId, long userId, Review review) {
        // Find food and user, throw exception if not found
        Food food = foodRepository.findById(foodId)
                .orElseThrow(() -> new RuntimeException("Food Not Found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        // Set review details
        review.setUserId(user);  // Associate the review with the user
        review.setFood(food);  // Associate the review with the food
        reviewRepository.save(review);  // Save the review

        // Add review to the food's review list
        food.getReviews().add(review);
        foodRepository.save(food);  // Save the updated food entity

        // Return the updated list of reviews
        return food.getReviews();
    }
}




