package de.telran.onlineshopgarden.mapper;

import de.telran.onlineshopgarden.dto.CategoryDto;
import de.telran.onlineshopgarden.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "name", source = "category")
    @Mapping(target = "categoryId", ignore = true)
    Category dtoToEntity(CategoryDto dto);

    @Mapping(target = "category", source = "name")
    CategoryDto entityToDto(Category entity);

    List<CategoryDto> entityListToDto(List<Category> entities);
}
