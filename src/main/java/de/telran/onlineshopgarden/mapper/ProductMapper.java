package de.telran.onlineshopgarden.mapper;

import de.telran.onlineshopgarden.dto.ProductCreateDto;
import de.telran.onlineshopgarden.dto.ProductDto;
import de.telran.onlineshopgarden.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "categoryId", source = "category.categoryId")
    @Mapping(target = "image", source = "imageUrl")
    ProductDto entityToDto(Product product);

    @Mapping(target = "category", ignore = true)
    @Mapping(target = "imageUrl", source = "image")
    @Mapping(target = "discountPrice", ignore = true)
    @Mapping(target = "productId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Product createDtoToEntity(ProductCreateDto dto);

    @Mapping(target = "productId", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "imageUrl", source = "image")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Product updateDtoToEntity(ProductDto dto);

    List<ProductDto> entityListToDtoList(List<Product> productList);
}