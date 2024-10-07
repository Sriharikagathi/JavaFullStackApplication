package com.Food_order_website.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;

    private String email;

    private String mobile;

    private String address;

    private Role role;

    @JsonIgnore
    @OneToMany
    private List<Food> favouriteFoods = new ArrayList<>();

    @JsonIgnore
    @OneToOne
    private Cart cart;

}
