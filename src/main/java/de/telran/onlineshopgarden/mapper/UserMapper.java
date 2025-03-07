package de.telran.onlineshopgarden.mapper;

import de.telran.onlineshopgarden.dto.UserCreateDto;
import de.telran.onlineshopgarden.dto.UserDto;
import de.telran.onlineshopgarden.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto entityToDto(User entity);

    List<UserDto> entityListToDto(List<User> entities);

    @Mapping(target = "passwordHash", ignore = true)
    @Mapping(target = "role", constant = "CLIENT")  // todo (with security) ???
    @Mapping(target = "userId", ignore = true)
    User createDtoToEntity(UserCreateDto dto);
}
