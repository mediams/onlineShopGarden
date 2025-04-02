package de.telran.onlineshopgarden.controller;

import de.telran.onlineshopgarden.dto.UserCreateDto;
import de.telran.onlineshopgarden.dto.UserDto;
import de.telran.onlineshopgarden.dto.UserUpdateDto;
import de.telran.onlineshopgarden.service.UserService;
import jakarta.validation.Valid;
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
    public List<UserDto> getAll() {
        return service.getAll();
    }

    @GetMapping("{userId}")
    public UserDto getById(@PathVariable Integer userId) {
        return service.getById(userId);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@Valid @RequestBody UserCreateDto dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login() {
        // TODO: implement
        throw new UnsupportedOperationException();
    }

    @PutMapping("{userId}")
    public ResponseEntity<UserDto> update(@PathVariable Integer userId, @Valid @RequestBody UserUpdateDto dto) {
        return new ResponseEntity<>(service.update(userId, dto), HttpStatus.OK);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<Void> delete(@PathVariable Integer userId) {
        service.delete(userId);
        return ResponseEntity.ok().build();
    }
}