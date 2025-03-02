package de.telran.onlineshopgarden.controller;

import de.telran.onlineshopgarden.entity.User;
import de.telran.onlineshopgarden.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<User> getAll() {
        return service.getAll();
    }

    @GetMapping("{id}")
    public User getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return new ResponseEntity<>(service.create(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login() {
        // TODO: implement
        throw new UnsupportedOperationException();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody User user) {
        service.update(id, user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}