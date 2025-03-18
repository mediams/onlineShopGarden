package de.telran.onlineshopgarden.service;

import de.telran.onlineshopgarden.dto.OrderItemFullDto;
import de.telran.onlineshopgarden.entity.OrderItem;
import de.telran.onlineshopgarden.exception.ResourceNotFoundException;
import de.telran.onlineshopgarden.mapper.OrderItemMapper;
import de.telran.onlineshopgarden.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {
    private final OrderItemRepository repository;
    private final OrderItemMapper mapper;

    @Autowired
    public OrderItemService(OrderItemRepository repository, OrderItemMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<OrderItemFullDto> getAll() {
        return mapper.entityListToFullDtoList(repository.findAll());
    }

    public OrderItemFullDto getById(int id) {
        OrderItem orderItem = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Orderitem with id %d not found", id)));
        return mapper.entityToFullDto(orderItem);
    }
}
