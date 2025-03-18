package de.telran.onlineshopgarden.controller;

import de.telran.onlineshopgarden.dto.ProductDto;
import de.telran.onlineshopgarden.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorites")
public class FavoriteController {

    private final FavoriteService service;

    @Autowired
    public FavoriteController(FavoriteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> addToFavorites(@RequestParam Integer userId, @RequestParam Integer productId) {
        // TODO: JWT аутентификация
        service.addToFavorites(userId, productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getUserFavorites(@RequestParam Integer userId) {
        // TODO: JWT аутентификация
        List<ProductDto> favorites = service.getFavoriteProducts(userId);
        return new ResponseEntity<>(favorites, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> removeFromFavorites(@RequestParam Integer userId, @RequestParam Integer productId) {
        // TODO: JWT аутентификация
        service.removeFromFavorites(userId, productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}