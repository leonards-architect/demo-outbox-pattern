package com.example.infrastructure;

import com.example.domain.Order;
import com.example.domain.OrderRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class OrderRepositoryAdaptor implements OrderRepository {

    OrderJpaRepository orderJpaRepository;

    OrderOutboxJpaRepository orderOutboxJpaRepository;

    @Override
    public void save(Order order) {
        orderJpaRepository.save(order);
        OrderOutbox orderOutbox = OrderOutbox.createOrder(order);
        orderOutboxJpaRepository.save(orderOutbox);
    }
}
