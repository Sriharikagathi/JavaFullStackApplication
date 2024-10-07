package com.Food_order_website.controller;


import com.Food_order_website.dto.CartDto;
import com.Food_order_website.dto.FoodDto;
import com.Food_order_website.entity.Cart;
import com.Food_order_website.entity.Food;
import com.Food_order_website.entity.Review;
import com.Food_order_website.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/food")
public class FoodController {

    @Autowired
    private FoodService foodService;

     @PostMapping
      public ResponseEntity<Food> CreateFood(@RequestBody Food food) {
          Food food1 = foodService.addFood(food);
          return ResponseEntity.ok(food1);
      }
      @PutMapping("/updateFood")
      public ResponseEntity<FoodDto> UpdateFood(@RequestBody FoodDto foodDto, @RequestParam long foodId, boolean vegetarian, boolean nonVegetarian) {
          FoodDto foodDto1 = foodService.updateFood(foodId, foodDto, vegetarian, nonVegetarian);
          return new ResponseEntity<>(foodDto1,HttpStatus.CREATED);
      }
      @DeleteMapping("/{foodId}")
      public ResponseEntity<String> DeleteFood(@PathVariable long foodId) {
         foodService.deleteFood(foodId);
         return ResponseEntity.ok("Food deleted successfully");

     }

     @GetMapping("/{foodId}")
     public ResponseEntity<Food> GetFoodById(@PathVariable long foodId) {
         Food food = foodService.getFoodById(foodId);
         return ResponseEntity.ok(food);
     }

     @GetMapping("/getAllFoods")
     public ResponseEntity<List<Food>> GetAllFoods() {
         List<Food> allFoods = foodService.findAllFoods();
         return ResponseEntity.ok(allFoods);
     }

     @GetMapping("/getAllVegetarianFoods")
     public ResponseEntity<List<Food>> isVegetarian( boolean vegetarian) {
         List<Food> vegetarian1 = foodService.isVegetarian(vegetarian);
         return ResponseEntity.ok(vegetarian1);
     }

     @GetMapping("/getAllNonVegetarianFoods")
     public ResponseEntity <?> isNonVegetarian( boolean nonVegetarian) throws Exception {
         List<Food> nonVegetarian1 = foodService.isNonVegetarian(nonVegetarian);
         if (nonVegetarian1.isEmpty()==true){
             return ResponseEntity.status(HttpStatus.NOT_FOUND)
                     .body("Non-vegetarian food not found");
         }
         return ResponseEntity.ok(nonVegetarian1);
     }

     @GetMapping("/getAllBestSellerFoods")
     public ResponseEntity<List<Food>> isBestSeller(boolean bestSeller) {
         List<Food> bestSeller1 = foodService.isBestSeller(bestSeller);
         return ResponseEntity.ok(bestSeller1);
     }



     @PostMapping("/giveFoodReview/{foodId}/{userId}")
     public ResponseEntity<List<Review>> FoodReview(@PathVariable long foodId,@PathVariable long userId,@RequestBody Review review) {
         List<Review> reviews = foodService.FoodReview(foodId,userId,review);
         return ResponseEntity.ok(reviews);
     }

}
