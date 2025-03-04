package de.telran.onlineshopgarden.controller;

import de.telran.onlineshopgarden.entity.Favorite;
import de.telran.onlineshopgarden.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
