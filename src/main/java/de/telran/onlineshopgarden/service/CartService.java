package de.telran.onlineshopgarden.service;

import de.telran.onlineshopgarden.dto.CartItemAddDto;
import de.telran.onlineshopgarden.entity.Cart;
import de.telran.onlineshopgarden.entity.CartItem;
import de.telran.onlineshopgarden.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CartService {
    private final CartRepository repository;

    @Autowired
    public CartService(CartRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void addItem(CartItemAddDto dto, int userId) {
        final Cart cart = repository.findCartByUserId(userId)
                .orElse(new Cart(userId));

        Integer productId = Integer.parseInt(dto.getProductId());

        cart.getItems().stream()
                .filter(i -> i.getProductId().equals(productId))
                .findFirst()
                .ifPresentOrElse(
                        i -> i.setQuantity(i.getQuantity() + dto.getQuantity()),
                        () -> cart.getItems().add(new CartItem(null, cart, productId, dto.getQuantity()))
                );

        repository.save(cart);
    }
}