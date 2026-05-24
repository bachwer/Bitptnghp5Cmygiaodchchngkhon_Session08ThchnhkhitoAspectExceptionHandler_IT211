package org.example.ex5.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.ex5.dto.PlaceOrderRequest;
import org.example.ex5.dto.PlaceOrderResponse;
import org.example.ex5.service.PlaceOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final PlaceOrderService placeOrderService;

    @PostMapping("/place")
    public ResponseEntity<PlaceOrderResponse> placeOrder(
        @Valid @RequestBody PlaceOrderRequest request,
        @RequestHeader("X-User") String username
    ) {
        PlaceOrderResponse response = placeOrderService.placeOrder(request, username);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

