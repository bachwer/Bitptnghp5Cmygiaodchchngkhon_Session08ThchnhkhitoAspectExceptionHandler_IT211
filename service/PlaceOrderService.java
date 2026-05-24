package org.example.ex5.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.ex5.dto.PlaceOrderRequest;
import org.example.ex5.dto.PlaceOrderResponse;
import org.example.ex5.entity.StockOrder;
import org.example.ex5.exception.MarginViolationException;
import org.example.ex5.repository.StockOrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PlaceOrderService {
    private final StockOrderRepository stockOrderRepository;
    private static final Double REFERENCE_PRICE = 100.0;
    private static final Double MARGIN_THRESHOLD = 0.07; // 7%

    public PlaceOrderResponse placeOrder(PlaceOrderRequest request, String username) {
        log.info("Core business logic: Processing order placement for user: {}", username);

        // Validate price margin (7% threshold)
        Double priceDeviation = Math.abs(request.getPrice() - REFERENCE_PRICE) / REFERENCE_PRICE;
        if (priceDeviation > MARGIN_THRESHOLD) {
            log.error("Margin violation detected. Price deviation: {}", priceDeviation);
            throw new MarginViolationException(
                String.format("Price %.2f deviates more than 7%% from reference price %.2f",
                    request.getPrice(), REFERENCE_PRICE)
            );
        }

        // Create and save the order
        StockOrder order = new StockOrder();
        order.setStockCode(request.getStockCode());
        order.setQuantity(request.getQuantity());
        order.setPrice(request.getPrice());
        order.setOrderType(request.getOrderType());
        order.setUsername(username);

        StockOrder savedOrder = stockOrderRepository.save(order);

        log.info("Order successfully placed with ID: {}", savedOrder.getId());

        return new PlaceOrderResponse(
            savedOrder.getId(),
            savedOrder.getStockCode(),
            savedOrder.getQuantity(),
            savedOrder.getPrice(),
            savedOrder.getOrderType(),
            "SUCCESS",
            "Order placed successfully"
        );
    }
}


