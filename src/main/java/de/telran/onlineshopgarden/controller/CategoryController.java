package de.telran.onlineshopgarden.controller;

import de.telran.onlineshopgarden.dto.CategoryDto;
import de.telran.onlineshopgarden.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService service;

    @Autowired
    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("{categoryId}")
    public ResponseEntity<CategoryDto> getById(@PathVariable Integer categoryId) {
        return ResponseEntity.ok(service.getById(categoryId));
    }

    @PostMapping
    public ResponseEntity<CategoryDto> create(@RequestBody CategoryDto dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("{categoryId}")
    public ResponseEntity<CategoryDto> update(@PathVariable Integer categoryId, @RequestBody CategoryDto dto) {
        return ResponseEntity.ok(service.update(categoryId, dto));
    }
}