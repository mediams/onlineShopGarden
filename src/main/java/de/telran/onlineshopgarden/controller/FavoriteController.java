package de.telran.onlineshopgarden.controller;

import de.telran.onlineshopgarden.entity.Product;
import de.telran.onlineshopgarden.entity.Favorite;
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

    @GetMapping
    public ResponseEntity<List<Favorite>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @PostMapping("/{userId}/{productId}")
    public ResponseEntity<String> addToFavorites(@PathVariable Integer userId, @PathVariable Integer productId) {
        service.addToFavorites(userId, productId);
        return new ResponseEntity<>("Item is added to favorites", HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Product>> getUserFavorites(@PathVariable Integer userId) {
        List<Product> favorites = service.getFavoriteProducts(userId);
        return new ResponseEntity<>(favorites, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/{productId}")
    public ResponseEntity<String> removeFromFavorites(@PathVariable Integer userId, @PathVariable Integer productId) {
        service.removeFromFavorites(userId, productId);
        return new ResponseEntity<>("Item is deleted from favorites", HttpStatus.OK);
    }
}
