package de.telran.onlineshopgarden.controller;

import de.telran.onlineshopgarden.dto.CategoryDto;
import de.telran.onlineshopgarden.entity.Category;
import de.telran.onlineshopgarden.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<Category>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
    @GetMapping("{id}")
    public ResponseEntity<Category> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping("/categories")
    public ResponseEntity<Category> create(@RequestBody CategoryDto dto) {
                return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Category> update(@PathVariable Integer categoryId, @RequestBody CategoryDto categoryDto) {
        Category updatedCategory = service.update(categoryId, categoryDto);
        return ResponseEntity.ok(updatedCategory);
    }
}
