package de.telran.onlineshopgarden.controller;

import de.telran.onlineshopgarden.dto.CategoryDto;
import de.telran.onlineshopgarden.entity.Category;
import de.telran.onlineshopgarden.mapper.CategoryMapper;
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
    private final CategoryMapper mapper;


    @Autowired
    public CategoryController(CategoryService service, CategoryMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAll() {
        List<CategoryDto> categoryDtos = service.getAll()
                .stream()
                .map(mapper::entityToDto)
                .toList();
        return ResponseEntity.ok(categoryDtos);
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoryDto> getById(@PathVariable Integer id) {
        Category category = service.getById(id);
        return ResponseEntity.ok(mapper.entityToDto(category));
    }

    @PostMapping
    public ResponseEntity<CategoryDto> create(@RequestBody CategoryDto dto) {
        Category createdCategory = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.entityToDto(createdCategory));
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> update(@PathVariable Integer categoryId, @RequestBody CategoryDto categoryDto) {
        Category updatedCategory = service.update(categoryId, categoryDto);
        return ResponseEntity.ok(mapper.entityToDto(updatedCategory));
    }
}
