package de.telran.onlineshopgarden.controller;

import de.telran.onlineshopgarden.dto.ProductDto;
import de.telran.onlineshopgarden.dto.ProductsFilterRequest;
import de.telran.onlineshopgarden.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/products")
@Tag(name = "Products", description = "REST API for managing products in the app")
public class ProductController {
    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public Page<ProductDto> getProducts(
            ProductsFilterRequest filterRequest,
            @ParameterObject @PageableDefault(size = 5, sort = "name") Pageable pageable
    ) {
        return service.getFiltered(filterRequest, pageable);
    }

    @Operation(summary = "Get product by id")
    @GetMapping("{productId}")
    public ResponseEntity<ProductDto> getById(@PathVariable Integer productId) {
        return ResponseEntity.ok(service.getById(productId));
    }

    @Operation(summary = "Get product with highest discount")
    @GetMapping("/productOfTheDay")
    public ResponseEntity<ProductDto> getProductOfTheDay() {
        return ResponseEntity.ok(service.getProductOfTheDay());
    }

    @Operation(summary = "Create new product")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @PostMapping()
    public ResponseEntity<ProductDto> create(@Valid @RequestBody ProductDto dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @Operation(summary = "Update product by id")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @PutMapping("{productId}")
    public ResponseEntity<ProductDto> update(@PathVariable Integer productId, @Valid @RequestBody ProductDto dto) {
        return new ResponseEntity<>(service.update(productId, dto), HttpStatus.OK);
    }

    @Operation(summary = "Set discount price for product")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @PatchMapping("{productId}")
    public ResponseEntity<ProductDto> setDiscountPrice(@PathVariable Integer productId,
                                                       @RequestParam(required = false) BigDecimal discountPrice) {
        ProductDto updatedProduct = service.setDiscountPrice(productId, discountPrice);
        return ResponseEntity.ok(updatedProduct);
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @DeleteMapping("{productId}")
    public ResponseEntity<Valid> delete(@PathVariable Integer productId) {
        service.delete(productId);
        return ResponseEntity.ok().build();
    }
}