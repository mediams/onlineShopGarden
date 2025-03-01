package de.telran.onlineshopgarden.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cart_items")
@NoArgsConstructor
@AllArgsConstructor

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
