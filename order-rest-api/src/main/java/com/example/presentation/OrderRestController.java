package com.example.presentation;

import com.example.application.OrderCommandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;
import java.util.UUID;

@RestController
public record OrderRestController(OrderCommandService orderCommandService) {

    @PostMapping("/orders")
    public ResponseEntity<Void> create() {
        SecureRandom random = new SecureRandom();
        orderCommandService.create(random.nextInt());
        return ResponseEntity.ok().build();
    }
}
