package de.telran.onlineshopgarden.mapper;

import de.telran.onlineshopgarden.dto.CategoryDto;
import de.telran.onlineshopgarden.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.net.MalformedURLException;
import java.net.URL;

@Mapper(componentModel = "spring",uses = CategoryMapper.class)
public interface CategoryMapper {

    @Mapping(target = "categoryId", expression = "java(category.getCategoryId().toString())")
    @Mapping(target = "imageUrl", expression = "java(category.getImageUrl().toString())")
    CategoryDto EntityToDto(Category category);

    @Named( "stringToUrl")
    @Mapping(target = "categoryId", expression = "java(Integer.parseInt(categoryDto.getCategoryId()))")
    @Mapping(target = "imageUrl", source = "imageUrl", qualifiedByName = "stringToUrl")
    Category DtoToEntity(CategoryDto categoryDto);

    @Named("stringToUrl")
    default URL stringToUrl(String imageUrl) {
        try {
            return new URL(imageUrl);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid URL format: " + imageUrl, e);
        }
    }
}
