package de.telran.onlineshopgarden.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemAddDto {

    private String productId;

    @NotNull(message = "{validation.cartItem.quantityNotBlank}")
    @Min(value = 1, message = "{validation.cartItem.quantityMin}")
    private Integer quantity;

}