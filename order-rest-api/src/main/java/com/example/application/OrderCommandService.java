package com.example.application;

import com.example.domain.Order;
import com.example.domain.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class OrderCommandService {

    OrderRepository orderRepository;

    @Transactional
    public void create(int customerId) {
        Order order = Order.of(customerId);
        orderRepository.save(order);
    }
}
