package com.example.demo.service;

import com.example.demo.controller.OrderController;
import com.example.demo.controller.OrderController.BasketItem;
import com.example.demo.entity.*;
import com.example.demo.repo.OrderItemRepository;
import com.example.demo.repo.OrderRepository;
import com.example.demo.repo.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private OrderItemRepository orderItemRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    public void testCreateOrder() {
        User user = new User();
        BasketItem basketItem = new BasketItem(1, 2);

        when(orderRepository.save(any(Order.class))).thenReturn(new Order());
        when(productRepository.findById(1)).thenReturn(java.util.Optional.of(new Product()));

        String response = orderService.createOrder(List.of(basketItem), user);
        verify(orderRepository, times(1)).save(any(Order.class));
        verify(orderItemRepository, times(1)).save(any(OrderItem.class));
    }
}
