package de.telran.onlineshopgarden.controller;

import de.telran.onlineshopgarden.dto.OrderDto;
import de.telran.onlineshopgarden.entity.Order;
import de.telran.onlineshopgarden.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    @Autowired
    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<Order> getAll() {
        return service.getAll();
    }

    @GetMapping("{orderId}")
    //TODO return STATUS
    public ResponseEntity<Order> getById(@PathVariable Integer orderId) {
        return ResponseEntity.ok(service.getById(orderId));
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<List<OrderDto>> getOrderHistory(@PathVariable Integer userId) {
        List<OrderDto> orderHistory = service.getOrderHistory(userId);
        return ResponseEntity.ok(orderHistory);
    }
}