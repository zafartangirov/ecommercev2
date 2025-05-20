package com.example.demo.service;


import com.example.demo.controller.OrderController.BasketItem;
import com.example.demo.entity.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    String createOrder(List<BasketItem> basket, User user);

    List<OrderResponse> getAllOrders(User user);
}
