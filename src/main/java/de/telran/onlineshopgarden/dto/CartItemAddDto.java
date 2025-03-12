package de.telran.onlineshopgarden.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemAddDto {

    private String productId;

    private Integer quantity;
}