package de.telran.onlineshopgarden.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

public class CartItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartItemId;
    private Integer cart_id;
    private Integer productId;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "cart_items")
    private Category category;

}
