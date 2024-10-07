package com.Food_order_website.repository;

import com.Food_order_website.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}