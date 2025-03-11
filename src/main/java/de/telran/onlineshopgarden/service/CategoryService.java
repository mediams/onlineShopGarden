package de.telran.onlineshopgarden.service;

import de.telran.onlineshopgarden.dto.CategoryDto;
import de.telran.onlineshopgarden.entity.Category;
import de.telran.onlineshopgarden.exception.ResourceNotFoundException;
import de.telran.onlineshopgarden.mapper.CategoryMapper;
import de.telran.onlineshopgarden.repository.CategoryRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
@Transactional(readOnly = true)
public class CategoryService {
    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    @Autowired
    public CategoryService(CategoryRepository repository, CategoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<CategoryDto> getAll() {
        return mapper.entityListToDto(repository.findAll());
    }

    public CategoryDto getById(Integer id) {
        Category category = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Category with id %d not found", id)));
        return mapper.entityToDto(category);
    }

    @Transactional
    public CategoryDto create(@Valid CategoryDto dto) {
        Category category = mapper.dtoToEntity(dto);
        return mapper.entityToDto(repository.save(category));
    }

    @Transactional
    public CategoryDto update(Integer id, @Valid CategoryDto dto) {
        Category category = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Category with id %d not found", id)));
        category.setName(dto.getName());
        category.setImageUrl(dto.getImageUrl());
        return mapper.entityToDto(category);
    }
}