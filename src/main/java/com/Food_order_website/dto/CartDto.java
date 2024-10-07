package com.Food_order_website.dto;

import lombok.Data;
import org.aspectj.bridge.IMessage;
import org.aspectj.bridge.Message;

@Data
public class CartDto {

    private long  id;

    private String foodName;

    private String foodDescription;

    private String foodImage;

    private Long foodPrice;

    private String address;

    private Double totalPrice;

    private int quantity;
}
