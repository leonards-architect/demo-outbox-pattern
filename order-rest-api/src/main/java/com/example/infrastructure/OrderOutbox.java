package com.example.infrastructure;

import com.example.domain.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "orders_outbox")
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class OrderOutbox {
    @Id
    String eventId;

    int aggregateId;

    String aggregateType;

    String eventType;

    String payload;

    Timestamp eventDate;

    String status;

    Timestamp publishedDate;

    static OrderOutbox createOrder(Order order) {
        String eventId = UUID.randomUUID().toString();
        int aggregateId = order.getOrderId();
        String aggregateType = order.getClass().getSimpleName();
        String eventType = "CREATE";
        ObjectMapper objectMapper = new ObjectMapper();
        String payload = "";
        try {
            payload = objectMapper.writeValueAsString(order);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        String status = "PENDING";
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        return new OrderOutbox(eventId, aggregateId, aggregateType, eventType, payload, now, status, now);
    }
}
