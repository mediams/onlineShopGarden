package de.telran.onlineshopgarden.service;

import de.telran.onlineshopgarden.dto.UserCreateDto;
import de.telran.onlineshopgarden.dto.UserDto;
import de.telran.onlineshopgarden.dto.UserUpdateDto;
import de.telran.onlineshopgarden.entity.User;
import de.telran.onlineshopgarden.exception.AccessForbiddenException;
import de.telran.onlineshopgarden.exception.ResourceNotFoundException;
import de.telran.onlineshopgarden.mapper.UserMapper;
import de.telran.onlineshopgarden.repository.CartItemRepository;
import de.telran.onlineshopgarden.repository.CartRepository;
import de.telran.onlineshopgarden.repository.FavoriteRepository;
import de.telran.onlineshopgarden.repository.UserRepository;
import de.telran.onlineshopgarden.security.AuthService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final OrderService orderService;
    private final FavoriteRepository favoriteRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final EntityManager entityManager;
    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;

    @Autowired
    public UserService(UserRepository repository, UserMapper mapper, OrderService orderService, FavoriteRepository favoriteRepository, CartRepository cartRepository, CartItemRepository cartItemRepository, EntityManager entityManager, PasswordEncoder passwordEncoder, AuthService authService) {
        this.repository = repository;
        this.mapper = mapper;
        this.orderService = orderService;
        this.favoriteRepository = favoriteRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.entityManager = entityManager;
        this.passwordEncoder = passwordEncoder;
        this.authService = authService;
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
        user.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        return mapper.entityToDto(repository.save(user));
    }

   @Transactional
    public UserDto update(Integer id, UserUpdateDto dto) {
       String login = authService.getAuthInfo().getLogin();
       User user = repository.findUserByEmail(login).get();

       repository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));

       if (!id.equals(user.getUserId())) {
           throw new AccessForbiddenException("You cannot update another user");
       }

       user.setName(dto.getName());
        user.setPhoneNumber(dto.getPhone());
        return mapper.entityToDto(user);
    }

    @Transactional
    public void mask(Integer id) {
        String login = authService.getAuthInfo().getLogin();
        User user = repository.findUserByEmail(login).get();

        repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));

        if (!id.equals(user.getUserId())) {
            throw new AccessForbiddenException("You cannot delete another user");
        }

        favoriteRepository.deleteByUserUserId(id);

        if (user.getCart() != null) {
            cartItemRepository.deleteByCartId(user.getCart().getCartId());
            entityManager.flush();
            user.setCart(null);
        }
        cartRepository.deleteByUserUserId(id);

        orderService.anonymizeUserDataInOrders(id);

        user.setName("Deleted User");
        user.setEmail("deleted_" + id + "@example.com");
        user.setPhoneNumber("0000000000");
        user.setPasswordHash("DELETED");
    }

}