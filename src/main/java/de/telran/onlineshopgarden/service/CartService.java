package de.telran.onlineshopgarden.service;

import de.telran.onlineshopgarden.dto.CartDto;
import de.telran.onlineshopgarden.dto.CartItemAddDto;
import de.telran.onlineshopgarden.entity.Cart;
import de.telran.onlineshopgarden.entity.CartItem;
import de.telran.onlineshopgarden.exception.ResourceNotFoundException;
import de.telran.onlineshopgarden.mapper.CartItemMapper;
import de.telran.onlineshopgarden.mapper.CartMapper;
import de.telran.onlineshopgarden.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class CartService {
    private final CartRepository repository;
    private final CartMapper mapper;
    private final CartItemMapper cartItemMapper;

    @Autowired
    public CartService(CartRepository repository, CartMapper mapper, CartItemMapper cartItemMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.cartItemMapper = cartItemMapper;
    }

    @Transactional
    public void addItem(CartItemAddDto dto, int userId) {
        final Cart cart = repository.findCartByUserId(userId)
                .orElse(new Cart(userId));

        CartItem cartItem = cartItemMapper.dtoToEntity(dto);

        cart.getItems().stream()
                .filter(i -> i.getProductId().equals(cartItem.getProductId()))
                .findFirst()
                .ifPresentOrElse(
                        i -> i.setQuantity(i.getQuantity() + dto.getQuantity()),
                        () -> cart.getItems().add(new CartItem(null, cart, cartItem.getProductId(), cartItem.getQuantity()))
                );

        repository.save(cart);
    }

    public CartDto getByUserId(Integer userId) {
        Optional<Cart> optional = repository.findCartByUserId(userId);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Cart by user with id %d not found", userId));
        }
        return mapper.entityToDto(optional.get());
    }
}