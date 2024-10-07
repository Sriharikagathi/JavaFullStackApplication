package com.Food_order_website.controller;


import com.Food_order_website.entity.Cart;
import com.Food_order_website.entity.Food;
import com.Food_order_website.entity.Review;
import com.Food_order_website.entity.User;
import com.Food_order_website.repository.UserRepository;
import com.Food_order_website.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) throws Exception {
        User user1 = userService.CreateUser(user);
        return ResponseEntity.ok(user1);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getUsers() {
        List<User> allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable long id) throws Exception {
        try {
            // Fetch user by id, and if the user is not found, an exception is thrown
            User user = userService.getUserById(id);
            return ResponseEntity.ok(user); // Return user data if found
        } catch (Exception e) {
            // If user is not found, return a 404 status with a custom message
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User with id " + id + " not found");
        }
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user,@PathVariable long id) throws Exception {
        User user1 = userService.UpdateUser(user, id);
        return ResponseEntity.ok(user1);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id) throws Exception {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    @PostMapping("/{userId}/favorites/{foodId}")
    public ResponseEntity<?> addToFavorite(@PathVariable long userId, @PathVariable long foodId) {
        try {
            // Call the service method to add food to favorites
            Food favoriteFood = userService.AddToFavorite(foodId, userId);
            return ResponseEntity.ok(favoriteFood);  // Return the added food with 200 OK
        } catch (RuntimeException e) {
            // In case of an error, return a 404 Not Found status with the error message
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping("/getFavoriteFoods/{userId}")
    public ResponseEntity<List<Food>> getFavoriteFoods(@PathVariable long userId,User user) {
        List<Food> favoriteFoods = userService.getFavoriteFoods(userId, user);
        return ResponseEntity.ok(favoriteFoods);
    }

    @PostMapping ("/addToCart/{foodId}/{userId}")
    public ResponseEntity<Cart> addFoodToCart(@PathVariable long foodId, @PathVariable long userId, @RequestBody Cart cart) {
        Cart cart1 = userService.AddFoodToCart(foodId, userId,cart);
        return new ResponseEntity<>(cart1,HttpStatus.CREATED);
    }
}
