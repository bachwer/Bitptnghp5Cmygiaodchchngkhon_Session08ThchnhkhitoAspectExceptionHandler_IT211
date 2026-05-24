package org.example.ex5.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceOrderResponse {
    private Long orderId;
    private String stockCode;
    private Integer quantity;
    private Double price;
    private String orderType;
    private String status;
    private String message;
}

