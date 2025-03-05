package de.telran.onlineshopgarden.service;

import de.telran.onlineshopgarden.entity.OrderItem;
import de.telran.onlineshopgarden.exception.ResourceNotFoundException;
import de.telran.onlineshopgarden.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {
    private final OrderItemRepository repository;

    @Autowired
    public OrderItemService(OrderItemRepository repository) {
        this.repository = repository;
    }

    public List<OrderItem> getAll() {
        return repository.findAll();
    }

    public OrderItem getById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Orderitem with id %d not found", id)));
    }
}
