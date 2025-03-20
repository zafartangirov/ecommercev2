package com.example.demo.repo;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void testFindAllByUserId() {
        Order order = new Order();
        order.setStatus(OrderStatus.CREATED);
        order = orderRepository.save(order);

        List<Order> orders = orderRepository.findAllByUserId(order.getUser().getId());
        assertNotNull(orders);
    }
}
