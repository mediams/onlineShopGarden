package de.telran.onlineshopgarden.service;

import de.telran.onlineshopgarden.dto.UserCreateDto;
import de.telran.onlineshopgarden.dto.UserDto;
import de.telran.onlineshopgarden.dto.UserUpdateDto;
import de.telran.onlineshopgarden.entity.User;
import de.telran.onlineshopgarden.exception.ResourceNotFoundException;
import de.telran.onlineshopgarden.mapper.UserMapper;
import de.telran.onlineshopgarden.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository repository;

    private final UserMapper mapper;

    @Autowired
    public UserService(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<UserDto> getAll() {
        return mapper.entityListToDto(repository.findAll());
    }

    public UserDto getById(Integer id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));
        return mapper.entityToDto(user);
    }

    @Transactional
    public UserDto create(UserCreateDto dto) {
        User user = mapper.createDtoToEntity(dto);
        // TODO: add password encryption
        return mapper.entityToDto(repository.save(user));
    }

   @Transactional
    public UserDto update(Integer id, UserUpdateDto dto) {
        User user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));
        user.setName(dto.getName());
        user.setPhoneNumber(dto.getPhone());
        return mapper.entityToDto(user);
    }

    @Transactional
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("User with id " + id + " not found");
        }
        // TODO: improve this logic
        repository.deleteByIdWithoutSelect(id);
    }
}
