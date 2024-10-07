package com.Food_order_website.service;

import com.Food_order_website.entity.Cart;
import com.Food_order_website.entity.Food;
import com.Food_order_website.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    public User CreateUser(User user) throws Exception;

    public List<User> getAllUsers();

    public User getUserById(long id) throws Exception;

    public User UpdateUser(User user,long id) throws Exception;

    public void deleteUser(long id) throws Exception;

    public Food AddToFavorite(long foodId, long UserId);

    public List<Food> getFavoriteFoods(long UserId,User user);

    public Cart AddFoodToCart(long foodId, long userId, Cart cart);
}
