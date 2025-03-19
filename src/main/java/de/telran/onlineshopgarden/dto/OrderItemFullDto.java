package de.telran.onlineshopgarden.dto;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemFullDto {

    private String orderItemId;

    private String orderId;

    private String productId;

    @Min(value = 1, message = "{validation.orderItem.quantityMin}")
    private int quantity;

    private BigDecimal priceAtPurchase;
}
