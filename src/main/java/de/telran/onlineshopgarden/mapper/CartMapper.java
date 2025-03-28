package de.telran.onlineshopgarden.mapper;

import de.telran.onlineshopgarden.dto.CartDto;
import de.telran.onlineshopgarden.entity.Cart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {

    CartDto entityToDto(Cart cart);
}
