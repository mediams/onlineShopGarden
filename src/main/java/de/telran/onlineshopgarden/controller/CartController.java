package de.telran.onlineshopgarden.controller;

import de.telran.onlineshopgarden.dto.CartItemAddDto;
import de.telran.onlineshopgarden.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService service;

    @Autowired
    public CartController(CartService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> addItem(@RequestBody CartItemAddDto dto, @RequestParam int userId) {
        // todo: replace with real userId
        service.addItem(dto, userId);
        return ResponseEntity.ok().build();
    }
}