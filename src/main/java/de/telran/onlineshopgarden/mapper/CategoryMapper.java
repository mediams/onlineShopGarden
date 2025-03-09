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

    @Mapping(target = "imageUrl", source = "imageUrl", qualifiedByName = "stringToUrl")
    Category dtoToEntity(CategoryDto categoryDto);

    @Mapping(target = "imageUrl", source = "imageUrl", qualifiedByName = "urlToString")
    CategoryDto entityToDto(Category category);


    @Named("stringToUrl")
    default URL stringToUrl(String imageUrl) {
        try {
            return new URL(imageUrl);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid URL format: " + imageUrl, e);
        }
    }

    @Named("urlToString")
    default String urlToString(URL imageUrl) {
        return imageUrl.toString();
    }
}
