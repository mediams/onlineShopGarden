package de.telran.onlineshopgarden.controller;

import de.telran.onlineshopgarden.dto.*;
import de.telran.onlineshopgarden.security.AuthService;
import de.telran.onlineshopgarden.service.UserService;
import jakarta.security.auth.message.AuthException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "Users", description = "REST API for managing users in the app")
public class UserController {

    private final UserService service;
    private final AuthService authService;

    @Autowired
    public UserController(UserService service, AuthService authService) {
        this.service = service;
        this.authService = authService;
    }

    @Operation(summary = "Get all users")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @GetMapping("/all")
    public List<UserDto> getAll() {
        return service.getAll();
    }

    @Operation(summary = "Get user by id")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @GetMapping("{userId}")
    public UserDto getById(@PathVariable Integer userId) {
        return service.getById(userId);
    }

    @Operation(summary = "Register new user")
    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@Valid @RequestBody UserCreateDto dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @Operation(summary = "Login user")
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody JwtRequest authRequest) throws AuthException {
        final JwtResponse token = authService.login(authRequest);
        return ResponseEntity.ok(token);
    }

    @Operation(summary = "Update user by id")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @PutMapping("{userId}")
    public ResponseEntity<UserDto> update(@PathVariable Integer userId, @Valid @RequestBody UserUpdateDto dto) {
        return new ResponseEntity<>(service.update(userId, dto), HttpStatus.OK);
    }

    @Operation(summary = "Delete user by id")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @DeleteMapping("{userId}")
    public ResponseEntity<Void> mask(@PathVariable Integer userId) {
        service.mask(userId);
        return ResponseEntity.ok().build();
    }
}