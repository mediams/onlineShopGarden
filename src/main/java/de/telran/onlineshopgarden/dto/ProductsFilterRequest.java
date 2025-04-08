package de.telran.onlineshopgarden.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductsFilterRequest {

    private Integer category;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private boolean discount;
}