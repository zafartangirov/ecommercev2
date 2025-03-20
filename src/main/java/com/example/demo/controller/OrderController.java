package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.repo.OrderItemRepository;
import com.example.demo.repo.OrderRepository;
import com.example.demo.repo.ProductRepository;
import com.example.demo.entity.OrderStatus;
import com.example.demo.repo.UserRepository;
import com.example.demo.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@PreAuthorize("hasRole('USER')")
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public String createOrder(@RequestBody List<BasketItem> basket, @AuthenticationPrincipal User user) {
        return orderService.createOrder(basket, user);
    }
    @GetMapping
    public List<OrderResponse> getAllOrders(@AuthenticationPrincipal User user) {
        return orderService.getAllOrders(user);
    }

    @Data
    @AllArgsConstructor
    public static class BasketItem {
        private Integer productId;
        private Integer quantity;
    }
}
