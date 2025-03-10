package de.telran.onlineshopgarden.service;

import de.telran.onlineshopgarden.dto.ProductCreateDto;
import de.telran.onlineshopgarden.dto.ProductDto;
import de.telran.onlineshopgarden.entity.Category;
import de.telran.onlineshopgarden.entity.Product;
import de.telran.onlineshopgarden.exception.ResourceNotFoundException;
import de.telran.onlineshopgarden.mapper.ProductMapper;
import de.telran.onlineshopgarden.repository.CategoryRepository;
import de.telran.onlineshopgarden.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository repository;
    private final ProductMapper mapper;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductService(ProductRepository repository, ProductMapper mapper, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.categoryRepository = categoryRepository;
    }

    public List<ProductDto> getAll() {
        return mapper.entityListToDtoList(repository.findAll());
    }

    public ProductDto getById(Integer id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Product with id %d not found", id)));
        return mapper.entityToDto(product);
    }

    @Transactional
    public ProductDto create(ProductCreateDto dto) {
        Product product = mapper.createDtoToEntity(dto);
        if (dto.getCategory() != null) {
            Category category = categoryRepository.getReferenceById(dto.getCategory());
            product.setCategoryId(category.getCategoryId());
        }
        Product savedProduct = repository.save(product);
        return mapper.entityToDto(savedProduct);
    }

    @Transactional
    public ProductDto update(ProductCreateDto dto, Integer id) {
        Optional<Product> optional = repository.findById(id);
        if (optional.isPresent()) {
            Product product = mapper.createDtoToEntity(dto);
            if (dto.getCategory() != null) {
                Category category = categoryRepository.getReferenceById(dto.getCategory());
                product.setCategoryId(category.getCategoryId());
            }
            Product saved = repository.save(product);
            return mapper.entityToDto(saved);
        }
        throw new ResourceNotFoundException(String.format("Product with id %d not found", id));
    }
}