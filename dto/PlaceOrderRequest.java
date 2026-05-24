package org.example.ex5.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.ex5.validation.ValidStockCode;
import org.example.ex5.validation.ValidLotSize;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOrderRequest {
    @NotBlank(message = "Stock code cannot be blank")
    @ValidStockCode
    private String stockCode;

    @NotNull(message = "Quantity cannot be null")
    @ValidLotSize
    private Integer quantity;

    @NotNull(message = "Price cannot be null")
    @DecimalMin(value = "0.1", message = "Price must be greater than 0")
    private Double price;

    @NotBlank(message = "Order type cannot be blank")
    @Pattern(regexp = "^(BUY|SELL)$", message = "Order type must be either BUY or SELL")
    private String orderType;
}

