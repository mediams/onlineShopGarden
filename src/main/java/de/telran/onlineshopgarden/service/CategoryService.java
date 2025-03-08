package de.telran.onlineshopgarden.service;

import de.telran.onlineshopgarden.dto.CategoryDto;
import de.telran.onlineshopgarden.entity.Category;
import de.telran.onlineshopgarden.exception.BadRequestException;
import de.telran.onlineshopgarden.exception.ResourceNotFoundException;
import de.telran.onlineshopgarden.mapper.CategoryMapper;
import de.telran.onlineshopgarden.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository repository;
    private final CategoryMapper mapper;


    @Autowired
    public CategoryService(CategoryRepository repository, CategoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<Category> getAll() {
        return repository.findAll();
    }

    public Category getById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Category with id %d not found", id)));
    }

    public Category create(CategoryDto dto) {
        return repository.save(mapper.DtoToEntity(dto));

    }

    public Category update(Integer categoryId, CategoryDto categoryDto) {
        Category category = repository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Category with id %d not found", categoryId)));

        category.setName(categoryDto.getName());
        return repository.save(category);
    }
}
