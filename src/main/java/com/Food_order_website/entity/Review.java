package com.Food_order_website.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double rating;

    private String comment;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Food food;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="review_Restaurant")
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private User userId;


}
