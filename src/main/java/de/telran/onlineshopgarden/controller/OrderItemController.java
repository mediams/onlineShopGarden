package de.telran.onlineshopgarden.controller;

import de.telran.onlineshopgarden.dto.OrderItemFullDto;
import de.telran.onlineshopgarden.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// delete CONTROLLER
@RestController
@RequestMapping("/orderitems")
public class OrderItemController {
    private final OrderItemService service;

    @Autowired
    public OrderItemController(OrderItemService service) {
        this.service = service;
    }

    // delete CONTROLLER
    @GetMapping("/all")
    public ResponseEntity<List<OrderItemFullDto>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    // delete CONTROLLER
    @GetMapping("{id}")
    public ResponseEntity<OrderItemFullDto> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }
}
