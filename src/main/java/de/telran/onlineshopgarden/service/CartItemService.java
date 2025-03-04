package de.telran.onlineshopgarden.service;

import de.telran.onlineshopgarden.entity.CartItem;
import de.telran.onlineshopgarden.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {
    private final CartItemRepository repository;

    @Autowired
    public CartItemService(CartItemRepository repository) {
        this.repository = repository;
    }

    public List<CartItem> getAll() {
        return repository.findAll();
    }

    public Optional<CartItem> getById(Integer id) {
        return repository.findById(id);
    }
}
