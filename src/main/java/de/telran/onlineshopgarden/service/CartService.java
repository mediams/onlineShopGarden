package de.telran.onlineshopgarden.service;

import de.telran.onlineshopgarden.entity.Cart;
import de.telran.onlineshopgarden.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class CartService {
    private final CartRepository repository;

    @Autowired
    public CartService(CartRepository repository) {
        this.repository = repository;
    }

    public List<Cart> getAll() {
        return repository.findAll();
    }
    public Optional<Cart> getById(Integer id) {
        return repository.findById(id);
    }


}
