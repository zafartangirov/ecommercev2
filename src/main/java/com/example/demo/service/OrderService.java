package com.example.demo.service;


import com.example.demo.controller.OrderController;
import com.example.demo.controller.OrderController.BasketItem;
import com.example.demo.entity.*;
import com.example.demo.repo.OrderItemRepository;
import com.example.demo.repo.OrderRepository;
import com.example.demo.repo.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public String createOrder(List<BasketItem> basket, User user){
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

    public List<OrderResponse> getAllOrders(User user){
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
}
