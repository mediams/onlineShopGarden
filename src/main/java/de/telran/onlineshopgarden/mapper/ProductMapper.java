package de.telran.onlineshopgarden.mapper;

import de.telran.onlineshopgarden.dto.ProductCreateDto;
import de.telran.onlineshopgarden.dto.ProductDto;
import de.telran.onlineshopgarden.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto entityToDto(Product product);

    @Mapping(target = "productId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Product createDtoToEntity(ProductCreateDto dto);

    List<ProductDto> entityListToDtoList(List<Product> productList);
}
