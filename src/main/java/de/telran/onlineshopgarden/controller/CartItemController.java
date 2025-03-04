package de.telran.onlineshopgarden.controller;

import de.telran.onlineshopgarden.entity.CartItem;
import de.telran.onlineshopgarden.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cartitems")
public class CartItemController {
    private final CartItemService service;

    @Autowired
    public CartItemController(CartItemService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<CartItem> getAll() {
       return  service.getAll();
    }

    @GetMapping("{id}")
    public Optional<CartItem> getById(@PathVariable Integer id) {
         return service.getById(id);
       
    }
}
