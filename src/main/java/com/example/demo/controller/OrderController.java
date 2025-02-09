package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.repo.OrderItemRepository;
import com.example.demo.repo.OrderRepository;
import com.example.demo.repo.ProductRepository;
import com.example.demo.entity.OrderStatus;
import com.example.demo.repo.UserRepository;
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
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public String createOrder(@RequestBody List<BasketItem> basket, @AuthenticationPrincipal User user) {
        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.CREATED);
        order = orderRepository.save(order);

        for (BasketItem item : basket) {
            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setAmount(item.getQuantity());
            orderItemRepository.save(orderItem);
        }
        return "Order created successfully!";
    }
    @GetMapping
    public List<OrderResponse> getAllOrders(@AuthenticationPrincipal User user) {
        List<Order> orders = orderRepository.findAllByUserId(user.getId());
        return orders.stream().map(order -> {
            List<OrderItem> orderItems = orderItemRepository.findByOrder(order);

            List<OrderItemResponse> items = orderItems.stream()
                    .map(item -> new OrderItemResponse(
                            item.getProduct().getName(),
                            item.getProduct().getPrice(),
                            item.getAmount(),
                            item.getProduct().getPhoto()
                    )).toList();

            return new OrderResponse(
                    order.getId(),
                    order.getUser(),
                    order.getDateTime(),
                    order.getStatus().toString(),
                    items
            );
        }).toList();
    }

    @Setter
    @Getter
    static class BasketItem {
        private Integer productId;
        private Integer quantity;

    }
}
