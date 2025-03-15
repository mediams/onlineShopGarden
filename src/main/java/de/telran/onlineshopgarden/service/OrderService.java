package de.telran.onlineshopgarden.service;

import de.telran.onlineshopgarden.dto.OrderDto;
import de.telran.onlineshopgarden.dto.OrderCreateDto;
import de.telran.onlineshopgarden.dto.OrderDto;
import de.telran.onlineshopgarden.entity.Order;
import de.telran.onlineshopgarden.entity.OrderItem;
import de.telran.onlineshopgarden.entity.User;
import de.telran.onlineshopgarden.exception.ResourceNotFoundException;
import de.telran.onlineshopgarden.mapper.OrderMapper;
import de.telran.onlineshopgarden.repository.OrderRepository;
import de.telran.onlineshopgarden.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository repository;
    private final UserRepository userRepository;
    private final OrderMapper mapper;

    @Autowired
    public OrderService(OrderRepository repository, UserRepository userRepository, OrderMapper mapper) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    public List<OrderDto> getAll() {
        return mapper.entityListToDtoList(repository.findAll());
    }

    public Order getById(Integer orderId) {
        return repository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Order with id %d not found", orderId)));
    }

    public List<OrderDto> getOrderHistory(Integer userId) {
        List<Order> orders = repository.findByUserId(userId);
        return mapper.entityListToDtoList(orders);
    }

    public OrderDto create(OrderCreateDto orderCreateDto, int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User with id %d not found", userId)));
        Order order = mapper.createDtoToEntity(orderCreateDto);
//        OrderItem orderItem = new OrderItem(null, order, );
//        order.setOrderItems();
        order.setContactPhone(user.getPhoneNumber());
        order.setUser(user);
//        System.out.println(order);
        repository.save(order);
        return mapper.entityToDto(order);
    }
}
