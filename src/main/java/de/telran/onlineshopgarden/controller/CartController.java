package de.telran.onlineshopgarden.controller;

import de.telran.onlineshopgarden.entity.Cart;
import de.telran.onlineshopgarden.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService service;

    @Autowired
    public CartController(CartService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<Cart> getAll() {
        return service.getAll();
    }

    @GetMapping("{id}")
    public Optional<Cart> getById(@PathVariable Integer id) {
        return service.getById(id);
    }


}

