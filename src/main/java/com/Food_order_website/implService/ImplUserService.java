package com.Food_order_website.implService;

import com.Food_order_website.entity.Cart;
import com.Food_order_website.entity.Food;
import com.Food_order_website.entity.User;
import com.Food_order_website.repository.CartRepository;
import com.Food_order_website.repository.FoodRepository;
import com.Food_order_website.repository.UserRepository;
import com.Food_order_website.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Service
public class ImplUserService implements UserService {
    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Override
    public User CreateUser(User user) throws Exception {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> all = userRepository.findAll();
        return all;
    }

    @Override
    public User getUserById(long id) throws Exception {
        Optional<User> byId = userRepository.findById(id);
        return byId.get();
    }

    @Override
    public User UpdateUser(User user,long id) throws Exception {
        Optional<User> byId = userRepository.findById(id);
        User user1= new User();
        user1.setId(id);
        user1.setUsername(user.getUsername());
        user1.setEmail(user.getEmail());
        user1.setAddress(user.getAddress());
        user1.setMobile(user.getMobile());
        user1.setRole(user.getRole());
        return userRepository.save(user1);
    }

    @Override
    public void deleteUser(long id) throws Exception {
        userRepository.deleteById(id);
    }


    @Override
    public Food AddToFavorite(long foodId, long userId) {
        // Find food and user by their IDs, or throw an exception if not found
        Food food = foodRepository.findById(foodId).orElseThrow(() -> new RuntimeException("Food Not Found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User Not Found"));
        // Check if the food is already in the user's list of favorite foods
        if (user.getFavouriteFoods().contains(food)) {
            throw new RuntimeException("Food is already in the favorite list");
        } else {
            // Add food to user's favorite foods and save the updated user entity
            user.getFavouriteFoods().add(food);
            userRepository.save(user);  // Save the updated user to persist the changes
        }
        // Return the food that was added to favorites
        return food;
    }

    @Override
    public List<Food> getFavoriteFoods(long UserId,User user) {
        User user1 = userRepository.findById(UserId).orElseThrow(() -> new RuntimeException("User Not Found"));
        List<Food> favoriteFoods = user1.getFavouriteFoods();
        return favoriteFoods;
    }

    @Override
    public Cart AddFoodToCart(long foodId, long userId, Cart cart) {
        // Fetch the food item from the repository
        Food food = foodRepository.findById(foodId)
                .orElseThrow(() -> new RuntimeException("Food not found"));

        // Fetch the user from the repository
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        // Create a new Cart object
        Cart cart1 = new Cart();
        cart1.setFoodName(food.getFoodName());  // Set the food
        cart1.setUser(user);
        if (food.getRestaurant() != null) {
            cart1.setRestaurantName(food.getRestaurant().getRestaurantName());  // Set the restaurant
        } else {
            throw new RuntimeException("Restaurant Name Not Found");
        }
        cart1.setQuantity(cart.getQuantity());  // Set the quantity
        cart1.setFoodImageUrl(food.getFoodImage());  // Set the food image URL
        //cart1.setPrice(food.getFoodPrice());  // Set the price of one food item

        // Calculate the total price: price * quantity
        long totalPrice = food.getFoodPrice() * cart.getQuantity();
        cart.setTotalPrice(totalPrice);

        // Save the cart object to the repository
        return cartRepository.save(cart);
    }
}
