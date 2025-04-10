package de.telran.onlineshopgarden.controller;

import de.telran.onlineshopgarden.dto.ProductDto;
import de.telran.onlineshopgarden.service.FavoriteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorites")
@Tag(name = "Favorites", description = "REST API for managing favorites in the app")
public class FavoriteController {

    private final FavoriteService service;

    @Autowired
    public FavoriteController(FavoriteService service) {
        this.service = service;
    }

    @Operation(summary = "Add product to favorites")
    @PostMapping
    public ResponseEntity<Void> addToFavorites(@RequestParam Integer productId) {
        service.addToFavorites(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Get user's favorites")
    @GetMapping
    public ResponseEntity<List<ProductDto>> getUserFavorites() {
        List<ProductDto> favorites = service.getFavoriteProducts();
        return new ResponseEntity<>(favorites, HttpStatus.OK);
    }

    @Operation(summary = "Remove product from favorites")
    @DeleteMapping
    public ResponseEntity<Void> removeFromFavorites(@RequestParam Integer productId) {
        service.removeFromFavorites(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}